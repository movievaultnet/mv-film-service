package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.film.wrappers;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// TODO: This name is not ideal.
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FilmInfoWrapper {

	private String id;
	private String title;
	private Integer releaseYear;
	private String producingCountry;
	private String rating;
	private String poster;

}
