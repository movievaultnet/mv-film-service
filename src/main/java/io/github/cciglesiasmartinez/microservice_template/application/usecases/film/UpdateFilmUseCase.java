package io.github.cciglesiasmartinez.microservice_template.application.usecases.film;

import io.github.cciglesiasmartinez.microservice_template.domain.exception.WrongFilmIdException;
import io.github.cciglesiasmartinez.microservice_template.domain.model.film.Film;
import io.github.cciglesiasmartinez.microservice_template.domain.model.film.valueobjects.*;
import io.github.cciglesiasmartinez.microservice_template.domain.port.out.FilmRepository;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.film.requests.UpdateFilmRequest;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Envelope;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Meta;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.film.responses.UpdateFilmResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
@Slf4j
@AllArgsConstructor
public class UpdateFilmUseCase {

    private FilmRepository filmRepository;

    /**
     *
     * @param request
     * @return
     */
    public Envelope<UpdateFilmResponse> execute(UpdateFilmRequest request) {
        Film existing = filmRepository.findById(FilmId.of(request.getId()))
                .orElseThrow(() -> new WrongFilmIdException("Film ID not found"));

        Title title = request.getTitle() != null ? Title.of(request.getTitle()) : existing.title();
        Description description = request.getDescription() != null
                ? Description.of(request.getDescription()) : existing.description();
        ReleaseYear releaseYear = request.getReleaseYear() != null
                ? ReleaseYear.of(request.getReleaseYear()) : existing.releaseYear();
        ProducingCountry producingCountry = request.getProducingCountry() != null
                ? ProducingCountry.of(request.getProducingCountry()) : existing.producingCountry();
        Rating rating = request.getRating() != null ? Rating.of(request.getRating()) : existing.rating();
        Poster poster = request.getPoster() != null ? Poster.of(request.getPoster()) : existing.poster();

        Film updated = Film.of(existing.id(), null, title, description, releaseYear, producingCountry, rating, poster);
        updated = filmRepository.update(updated);

        UpdateFilmResponse data = new UpdateFilmResponse(
                updated.id().value(),
                updated.title().value(),
                updated.description().value(),
                updated.releaseYear().value(),
                updated.producingCountry().value(),
                updated.rating().value(),
                updated.poster().value()
        );

        return new Envelope<>(data, new Meta());
    }

}
