package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.edition.wrappers;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.Edition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Year;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class EditionWrapper {

    private String id;
    private String filmId;
    private String slug;
    private String coverPicture;
    private String barcode;
    private String country;
    private String format;
    private Year releaseYear;
    private String packagingType;
    private boolean verified;
    private String notes;

    private static String extractPictureFrom(Edition edition) {
        return !edition.pictures().isEmpty()
                ? edition.pictures().getFirst().url().value()
                : null;
    }

    public static EditionWrapper from(Edition edition) {
        return new EditionWrapper(
                edition.editionId().value(),
                edition.film().id().value(),
                edition.slug().value(),
                edition.coverPicture(),
                edition.barCode().value(),
                edition.country().value(),
                edition.format().name(),
                edition.releaseYear(),
                edition.packagingType().name(),
                true,
                edition.notes().value());
    }

}
