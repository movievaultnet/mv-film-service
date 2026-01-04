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
@Schema(description = "Payload to create a new film.")
public class CreateFilmRequest {

    @NotBlank
    @Size(max = 200)
    @Schema(description = "Film title", example = "Inception")
    private String title;

    @NotBlank
    @Size(max = 2000)
    @Schema(description = "Film description", example = "A thief who steals corporate secrets through dream-sharing technology.")
    private String description;

    @NotNull
    @Min(1888) // primeros registros de cine
    @Max(2100)
    @Schema(description = "Release year", example = "2010")
    private Integer releaseYear;

    @NotBlank
    @Size(max = 56)
    @Schema(description = "Producing country (ISO 3166-1 alpha-2 or name)", example = "US")
    private String producingCountry;

    @NotBlank
    @Size(max = 20)
    @Schema(description = "Content rating / classification", example = "PG-13")
    private String rating;

    @NotBlank
    @Size(max = 1024)
    @Pattern(regexp = "https?://.+", message = "poster must be a valid URL")
    @Schema(description = "Poster URL", example = "https://cdn.example.com/posters/inception.jpg")
    private String poster;
}
