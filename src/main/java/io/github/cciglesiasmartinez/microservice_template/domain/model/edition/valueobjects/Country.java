package io.github.cciglesiasmartinez.microservice_template.domain.model.edition.valueobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Country {

    private final String value;

    @JsonCreator
    private Country(@JsonProperty("value") String value) {
        this.value = value;
    }

    public static Country of(String country) {
        if ((country == null) || country.isBlank()) {
            throw new IllegalArgumentException("Country can't be null nor empty.");
        }
        return new Country(country);
    }

    public String value() {
        return this.value;
    }

    public String getValue() {
        return this.value;
    }

}
