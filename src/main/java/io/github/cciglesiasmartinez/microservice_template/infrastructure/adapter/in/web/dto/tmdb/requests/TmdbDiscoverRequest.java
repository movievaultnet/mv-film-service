package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.tmdb.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TmdbDiscoverRequest {

    @Schema(description = "Include adult titles", example = "false")
    @NotNull
    private Boolean includeAdult;

    @Schema(description = "Include video results", example = "false")
    @NotNull
    private Boolean includeVideo;

    @Schema(description = "Language code", example = "en-US")
    @NotBlank
    private String language;

    @Schema(description = "Result page", example = "1")
    @NotNull
    @Min(1)
    private Integer page;

    @Schema(description = "Sort criteria", example = "popularity.desc")
    @NotBlank
    private String sortBy;
}