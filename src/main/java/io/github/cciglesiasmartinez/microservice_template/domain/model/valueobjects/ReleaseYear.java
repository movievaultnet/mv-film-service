package io.github.cciglesiasmartinez.microservice_template.domain.model.valueobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class ReleaseYear {
	
	private final int value;
	
	@JsonCreator
	private ReleaseYear(@JsonProperty ("value") int value) {
		this.value = value;
		
	}
	
	public static ReleaseYear of(Integer releaseYear) {
		if (releaseYear == null || releaseYear.equals(0)) {
			throw new IllegalArgumentException("ReleaseYear can't be null or empty.");
		}
		return new ReleaseYear(releaseYear);
	}
	
	public int value() {
        return this.value;
    }

    public int getValue() {
        return this.value;
    }

}
