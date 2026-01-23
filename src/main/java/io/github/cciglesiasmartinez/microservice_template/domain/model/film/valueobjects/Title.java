package io.github.cciglesiasmartinez.microservice_template.domain.model.film.valueobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class Title {
	
	private final String value;
	
	@JsonCreator
	private Title(@JsonProperty("value") String value) {
		this.value = value;
	}

	public static Title of(String title) {
		if (title == null || title.isBlank() ) {
			throw new IllegalArgumentException("Title can't be null or empty.");
		}
		return new Title(title);
		
	}
	
	public String value() {
        return this.value;
    }

    public String getValue() {
        return this.value;
    }
	
}
