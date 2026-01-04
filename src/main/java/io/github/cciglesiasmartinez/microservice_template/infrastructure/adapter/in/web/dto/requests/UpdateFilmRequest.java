package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(description = "Payload to update an existing film (partial update). Only non-null fields will be updated.")
public class UpdateFilmRequest {

    @Schema(description = "Film id (uuidv4)", example = "1d5c39d2-8a2c-4d28-a6d4-e4e41090bbae")
    private String id;

    @Size(max = 200)
    @Schema(description = "Film title", example = "Inception (Director's Cut)")
    private String title;

    @Size(max = 2000)
    @Schema(description = "Film description", example = "Extended cut with additional dream layers.")
    private String description;

    @Min(1888) @Max(2100)
    @Schema(description = "Release year", example = "2011")
    private Integer releaseYear;

    @Size(max = 56)
    @Schema(description = "Producing country (ISO 3166-1 alpha-2 or name)", example = "US")
    private String producingCountry;

    @Size(max = 20)
    @Schema(description = "Content rating / classification", example = "PG-13")
    private String rating;

    @Size(max = 1024)
    @Pattern(regexp = "(|https?://.+)", message = "poster must be a valid URL")
    @Schema(description = "Poster URL", example = "https://cdn.example.com/posters/inception-cut.jpg")
    private String poster;
}
