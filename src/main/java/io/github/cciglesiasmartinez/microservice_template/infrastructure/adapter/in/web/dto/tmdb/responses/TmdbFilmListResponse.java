package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.tmdb.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.tmdb.wrappers.TmdbFilmWrapper;
import lombok.Data;
import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TmdbFilmListResponse {

    private Integer page;
    private List<TmdbFilmWrapper> results;
    private Integer totalPages;
    private Integer totalResults;

}