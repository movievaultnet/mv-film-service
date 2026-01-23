package io.github.cciglesiasmartinez.microservice_template.application.usecases.edition;

import io.github.cciglesiasmartinez.microservice_template.domain.event.DomainEventPublisher;
import io.github.cciglesiasmartinez.microservice_template.domain.event.edition.EditionCreatedEvent;
import io.github.cciglesiasmartinez.microservice_template.domain.exception.WrongFilmIdException;
import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.Edition;
import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.Picture;
import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.valueobjects.*;
import io.github.cciglesiasmartinez.microservice_template.domain.model.film.Film;
import io.github.cciglesiasmartinez.microservice_template.domain.model.film.valueobjects.FilmId;
import io.github.cciglesiasmartinez.microservice_template.domain.port.out.EditionRepository;
import io.github.cciglesiasmartinez.microservice_template.domain.port.out.FilmRepository;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Envelope;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Meta;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.edition.requests.CreateEditionRequest;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.edition.responses.CreateEditionResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;


/**
 * Application service for creating a new edition for a film.
 * <p>
 * Validates the provided film ID, creates a new {@link Edition} instance, persists it and
 * publishes an {@link EditionCreatedEvent} event.
 * <p>
 * TODO: Consider a future strategy against duplicate editions.
 */
@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class CreateEditionUseCase {

    private final EditionRepository editionRepository;
    private final FilmRepository filmRepository;
    private final DomainEventPublisher domainEventPublisher;

    /**
     * Retrieves a {@link Film} by its ID.
     *
     * @param filmId    the {@link FilmId} of the film to retrieve.
     * @return          the corresponding {@link Film} instance.
     *
     * @throws WrongFilmIdException if the film ID does not exist.
     */
    private Film getFilm(FilmId filmId) {
        return filmRepository.findById(filmId)
                .orElseThrow(() -> new WrongFilmIdException("Film ID not found"));
    }

    /**
     * Publishes an {@link EditionCreatedEvent} event after a new edition is created.
     *
     * @param edition   the newly created {@link Edition} instance.
     * @param film      the associated {@link Film} instance.
     */
    private void publishEditionCreatedEvent(Edition edition, Film film) {
        EditionCreatedEvent event = EditionCreatedEvent.builder()
                .editionId(edition.editionId().value())
                .filmId(film.id().value())
                .filmTitle(film.title().value())
                .slug(edition.slug().value())
                .coverPicture(edition.coverPicture())
                .barCode(edition.barCode().value())
                .country(edition.country().value())
                .format(edition.format().name())
                .releaseYear(edition.releaseYear())
                .build();
        domainEventPublisher.publish(event);
    }

    /**
     * Executes the create edition use case.
     *
     * @param request   the {@link CreateEditionRequest} containing edition details.
     * @return          an {@link Envelope} containing {@link CreateEditionResponse} with the result.
     *
     * @throws WrongFilmIdException if the provided film ID does not exist.
     */
    public Envelope<CreateEditionResponse> execute(CreateEditionRequest request) {
        FilmId filmId = FilmId.of(request.getFilmId());
        Film film = getFilm(filmId);
        BarCode barCode = BarCode.of(request.getBarCode());
        Country country = Country.of(request.getCountry());
        Format format = Format.valueOf(request.getFormat());
        Year releaseYear = Year.of(request.getReleaseYear());
        PackagingType packagingType = PackagingType.valueOf(request.getPackagingType());
        Notes notes = Notes.of(request.getNotes());
        List<Picture> pictures = new ArrayList<>();
        Edition edition = Edition.create(film, barCode, country, format, releaseYear, packagingType, notes, pictures);
        editionRepository.persist(edition);
        publishEditionCreatedEvent(edition, film);
        CreateEditionResponse data = new CreateEditionResponse(edition.editionId().value(), true);
        log.info("Edition {} successfully created.", edition.editionId().value());
        return new Envelope<>(data, new Meta());
    }

}
