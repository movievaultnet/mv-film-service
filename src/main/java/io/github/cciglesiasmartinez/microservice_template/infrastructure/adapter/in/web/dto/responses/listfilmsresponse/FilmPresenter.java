package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.listfilmsresponse;

import org.springframework.stereotype.Component;

import io.github.cciglesiasmartinez.microservice_template.domain.model.Film;

@Component
public class FilmPresenter {

    // ---------------------------------------------------------------------
    // Nueva versión: mapea a FilmInfo (el DTO de listado actual)
    // ---------------------------------------------------------------------
    public FilmInfo toInfo(Film f) {
        return new FilmInfo(
                f.itemId().value(),
                f.title().value(),
                f.releaseYear().value(),
                f.producingCountry().value(),
                f.rating().value(),
                f.poster() != null ? f.poster().value() : null
        );
    }

    // ---------------------------------------------------------------------
    // Versión anterior (comentada): mapeaba a FilmSummaryDto
    // Mantén comentado hasta eliminar todas las referencias a FilmSummaryDto
    // ---------------------------------------------------------------------
//    public FilmSummaryDto toSummaryDto(Film f) {
//        return new FilmSummaryDto(
//                f.itemId().value(),
//                f.title().value(),
//                f.releaseYear().value(),
//                f.producingCountry().value(),
//                f.rating().value(),
//                f.poster() != null ? f.poster().value() : null
//        );
//    }
}
