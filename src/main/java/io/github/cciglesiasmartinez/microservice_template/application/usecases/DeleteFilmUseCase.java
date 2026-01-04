package io.github.cciglesiasmartinez.microservice_template.application.usecases;

import io.github.cciglesiasmartinez.microservice_template.domain.model.valueobjects.FilmId;
import io.github.cciglesiasmartinez.microservice_template.domain.port.out.FilmRepository;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.DeleteFilmResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.Envelope;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.Meta;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
@AllArgsConstructor
@Slf4j
public class DeleteFilmUseCase {

    private FilmRepository filmRepository;

    /**
     *
     * @param id
     * @return
     */
    public Envelope<DeleteFilmResponse> execute(String id) {
        FilmId filmId = FilmId.of(id);
        boolean deleted = filmRepository.deleteById(filmId);

        DeleteFilmResponse data = new DeleteFilmResponse(filmId.value(), deleted);

        // Logging informativo
        if (deleted) {
            log.info("Film {} deleted.", filmId.value());
        } else {
            log.warn("Film {} not found to delete.", filmId.value());
        }

        return new Envelope<>(data, new Meta());
    }

}
