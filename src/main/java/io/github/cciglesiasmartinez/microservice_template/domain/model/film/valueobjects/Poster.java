package io.github.cciglesiasmartinez.microservice_template.domain.model.film.valueobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class Poster {

	private final String value;

    @JsonCreator
    private Poster(@JsonProperty("value") String value) {
        this.value = value;
    }
    
    public static Poster of(String poster) {
        if (poster == null || poster.isBlank()) {
            throw new IllegalArgumentException("Name can't be null or empty.");
        }
        return new Poster(poster);
    }

    public String value() {
        return this.value;
    }

    public String getValue() {
        return this.value;
    }
	
}
