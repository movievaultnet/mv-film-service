package io.github.cciglesiasmartinez.microservice_template.application.usecases.film;

import io.github.cciglesiasmartinez.microservice_template.domain.model.film.Film;
import io.github.cciglesiasmartinez.microservice_template.domain.model.film.valueobjects.*;
import io.github.cciglesiasmartinez.microservice_template.domain.port.out.FilmRepository;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.film.requests.CreateFilmRequest;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.film.responses.CreateFilmResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Envelope;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Meta;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Create film use case.
 * <p>
 * TODO: Needs strong strategy against dupes.
 */
@Service
@AllArgsConstructor
@Slf4j
public class CreateFilmUseCase {

    private final FilmRepository filmRepository;

    /**
     * Helper that creates a {@link Film} from a {@link CreateFilmRequest}.
     *
     * @param request the {@link CreateFilmRequest} we are going to use.
     * @return a {@link Film} instance
     */
    private Film createFilmFrom(CreateFilmRequest request) {
        return Film.create(
                TmdbId.of(request.getTmdbId()),
                Title.of(request.getTitle()),
                Description.of(request.getDescription()),
                ReleaseYear.of(request.getReleaseYear()),
                ProducingCountry.of(request.getProducingCountry()),
                Rating.of(request.getRating()),
                Poster.of(request.getPoster())
        );
    }

    /**
     * Helper method that creates a {@link CreateFilmResponse} from a {@link Film} instance.
     *
     * @param saved a {@link Film} instance.
     * @return {@link CreateFilmResponse} DTO containing the film information.
     */
    private CreateFilmResponse getCreateFilmResponseFrom(Film saved) {
        return new CreateFilmResponse(
                saved.id().value(),
                saved.title().value(),
                saved.description().value(),
                saved.releaseYear().value(),
                saved.producingCountry().value(),
                saved.rating().value(),
                saved.poster().value()
        );
    }

    /**
     * Executes create film use case.
     *
     * @param request {@link CreateFilmRequest} DTO containing film information.
     * @return {@link CreateFilmResponse} DTO containing film information.
     */
    public Envelope<CreateFilmResponse> execute(CreateFilmRequest request) {
        Film existingFilm = filmRepository.findByTmdbId(TmdbId.of(request.getTmdbId())).orElse(null);
        if (existingFilm != null) {
            CreateFilmResponse data = getCreateFilmResponseFrom(existingFilm);
            log.info("Film {} already exists, returning existing film.", existingFilm.id().value());
            return new Envelope<>(data, new Meta());
        }
        Film film = createFilmFrom(request);
        Film saved = filmRepository.save(film);
        CreateFilmResponse data = getCreateFilmResponseFrom(saved);
        log.info("Film {} has been successfully added by user {}", saved.id().value(), null);
        return new Envelope<>(data, new Meta());
    }

}
