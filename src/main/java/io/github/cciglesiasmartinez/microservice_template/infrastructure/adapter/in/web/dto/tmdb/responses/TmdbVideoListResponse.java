package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.tmdb.responses;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.tmdb.wrappers.TmdbVideoWrapper;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TmdbVideoListResponse {
	
	private Integer id;
	private List<TmdbVideoWrapper> results;

}
