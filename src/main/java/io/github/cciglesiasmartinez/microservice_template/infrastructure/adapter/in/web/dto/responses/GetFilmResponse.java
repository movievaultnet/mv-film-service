package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(description = "Film resource.")
public class GetFilmResponse {

    @Schema(
            description = "Item identifier (UUIDv4).",
            example = "57e9fc95-2a66-40e7-8fd3-debd19a661e3"
    )
    private String id;

    @Schema(
            description = "Film title.",
            example = "Inception"
    )
    private String title;

    @Schema(
            description = "Short description or synopsis.",
            example = "A thief who steals corporate secrets through dream-sharing technology is given the inverse task of planting an idea."
    )
    private String description;

    @Schema(
            description = "Release year.",
            example = "2010"
    )
    private Integer releaseYear;

    @Schema(
            description = "Producing country (ISO 3166-1 alpha-2 or name).",
            example = "US"
    )
    private String producingCountry;

    @Schema(
            description = "Content rating or classification.",
            example = "PG-13"
    )
    private String rating;

    @Schema(
            description = "Poster URL.",
            example = "https://cdn.example.com/posters/inception.jpg"
    )
    private String poster;
}
