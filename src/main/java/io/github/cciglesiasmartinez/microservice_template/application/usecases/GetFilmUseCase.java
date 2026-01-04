package io.github.cciglesiasmartinez.microservice_template.application.usecases;

import org.springframework.stereotype.Service;

import io.github.cciglesiasmartinez.microservice_template.domain.exception.WrongFilmIdException;
import io.github.cciglesiasmartinez.microservice_template.domain.model.Film;
import io.github.cciglesiasmartinez.microservice_template.domain.model.valueobjects.FilmId;
import io.github.cciglesiasmartinez.microservice_template.domain.port.out.FilmRepository;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.Envelope;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.GetFilmResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.Meta;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 *
 */
@Service
@AllArgsConstructor
@Slf4j
public class GetFilmUseCase {

    private final FilmRepository filmRepository;

    /**
     *
     * @param id
     * @return
     */
    public Envelope<GetFilmResponse> execute(String id) {
        FilmId filmId = FilmId.of(id);

        Film film = filmRepository.findById(filmId)
                .orElseThrow(() -> new WrongFilmIdException("Film ID not found"));

        GetFilmResponse data = new GetFilmResponse(
                film.itemId().value(),
                film.title().value(),
                film.description().value(),
                film.releaseYear().value(),
                film.producingCountry().value(),
                film.rating().value(),
                film.poster().value()
        );

        Meta meta = new Meta();
        log.info("Film {} successfully retrieved.", filmId.value());
        return new Envelope<>(data, meta);
    }
}
