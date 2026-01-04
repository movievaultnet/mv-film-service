package io.github.cciglesiasmartinez.microservice_template.domain.model.valueobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class Description {
	
	private final String value;
	
	@JsonCreator
	private Description(@JsonProperty("value") String value) {
		this.value = value;
	}
	
	public static Description of (String description) {
		if (description == null || description.isBlank()) {
			throw new IllegalArgumentException("Descripcion can't be null or empty.");
		}
		return new Description(description);
	}
	
	public String value() {
        return this.value;
    }

    public String getValue() {
        return this.value;
    }


}
