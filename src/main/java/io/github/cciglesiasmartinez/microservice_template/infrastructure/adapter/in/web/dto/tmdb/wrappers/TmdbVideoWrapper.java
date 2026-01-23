package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.tmdb.wrappers;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class TmdbVideoWrapper {
	private String site;
	private String type;
	private Boolean official;
	private String key;

}
