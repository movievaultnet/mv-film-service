package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.edition.responses;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.Edition;
import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.Picture;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Year;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Schema(description = "Edition resource.")
public class GetEditionResponse {

    private String id;
    private String filmId;

    private String slug;
    private String barcode;
    private String country;
    private String format;
    private Year releaseYear;
    private String packagingType;
    private boolean verified;
    private String notes;

    private List<GetPictureResponse> pictures;

    private static List<GetPictureResponse> createPictureListFrom(List<Picture> pictureList) {
        return pictureList.stream()
                .map((picture) -> new GetPictureResponse(
                        picture.id().value(),
                        picture.url().value(),
                        picture.uploadedAt()
                ))
                .toList();
    }

    public static GetEditionResponse from(Edition edition) {
        return new GetEditionResponse(
                edition.editionId().value(),
                edition.film().id().value(),
                edition.slug().value(),
                edition.barCode().value(),
                edition.country().value(),
                edition.format().name(),
                edition.releaseYear(),
                edition.packagingType().name(),
                true,
                edition.notes().value(),
                createPictureListFrom(edition.pictures()));
    }

}
