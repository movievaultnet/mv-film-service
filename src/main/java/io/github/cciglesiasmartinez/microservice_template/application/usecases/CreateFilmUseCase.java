package io.github.cciglesiasmartinez.microservice_template.application.usecases;

import io.github.cciglesiasmartinez.microservice_template.domain.model.Film;
import io.github.cciglesiasmartinez.microservice_template.domain.model.valueobjects.*;
import io.github.cciglesiasmartinez.microservice_template.domain.port.out.FilmRepository;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.requests.CreateFilmRequest;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.CreateFilmResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.Envelope;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.Meta;
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
     * Executes create film use case.
     *
     * @param request {@link CreateFilmRequest} DTO containing film information.
     * @return {@link CreateFilmResponse} DTO containing film information.
     */
    public Envelope<CreateFilmResponse> execute(CreateFilmRequest request) {
        Film film = Film.create(
                Title.of(request.getTitle()),
                Description.of(request.getDescription()),
                ReleaseYear.of(request.getReleaseYear()),
                ProducingCountry.of(request.getProducingCountry()),
                Rating.of(request.getRating()),
                Poster.of(request.getPoster())
        );

        Film saved = filmRepository.save(film);

        CreateFilmResponse data = new CreateFilmResponse(
                saved.itemId().value(),
                saved.title().value(),
                saved.description().value(),
                saved.releaseYear().value(),
                saved.producingCountry().value(),
                saved.rating().value(),
                saved.poster().value()
        );

        return new Envelope<>(data, new Meta());
    }

}
