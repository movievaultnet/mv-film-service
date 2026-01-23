package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.elastic;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

// TODO: Change this ugly name
// TODO: Trim this DTO properly

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(description = "Placeholder")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ElasticEditionWrapper {

    private String id;
    private String filmId;
    private String slug;
    private String filmTitle;
    private String coverPicture;
    private String barCode;
    private String country;
    private String format;
    private Integer releaseYear;
    private String packagingType;
    private String notes;
    private String searchableText;
    private LocalDateTime indexedAt;

}
