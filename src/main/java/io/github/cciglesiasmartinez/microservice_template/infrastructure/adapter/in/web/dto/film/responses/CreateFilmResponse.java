package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.film.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(description = "Response after creating a film.")
public class CreateFilmResponse {

    @Schema(description = "Film identifier", example = "9c5e0d8f-53d7-4b1e-9b97-3e8f1f2b0a3e")
    private String id;

    @Schema(description = "Film title", example = "Inception")
    private String title;

    @Schema(description = "Film description", example = "A thief who steals corporate secrets through dream-sharing technology.")
    private String description;

    @Schema(description = "Release year", example = "2010")
    private Integer releaseYear;

    @Schema(description = "Producing country (ISO 3166-1 alpha-2 or name)", example = "US")
    private String producingCountry;

    @Schema(description = "Rating", example = "PG-13")
    private String rating;

    @Schema(description = "Poster URL", example = "https://cdn.example.com/posters/inception.jpg")
    private String poster;
}
