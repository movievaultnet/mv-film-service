package io.github.cciglesiasmartinez.microservice_template.domain.model.valueobjects;



import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class ProducingCountry {
	
	private final String value;
	
	@JsonCreator
	private ProducingCountry(@JsonProperty("value")String value) {
		this.value = value;
	}
	
	public static ProducingCountry of(String lenguage) {
		if (lenguage == null || lenguage .isBlank()) {
			throw new IllegalArgumentException("Name can't be null or empty.");
		}
		return new ProducingCountry(lenguage);
		
	}
	public String value() {
        return this.value;
    }

    public String getValue() {
        return this.value;
    }
	

}
