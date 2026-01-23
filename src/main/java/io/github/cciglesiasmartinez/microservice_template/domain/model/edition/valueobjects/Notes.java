package io.github.cciglesiasmartinez.microservice_template.domain.model.edition.valueobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Notes {

    private final String value;

    @JsonCreator
    private Notes(@JsonProperty("value") String value) {
        this.value = value;
    }

    public static Notes of(String notes) {
        if ((notes == null) || notes.isBlank()) {
            throw new IllegalArgumentException("Notes can't be null nor empty.");
        }
        return new Notes(notes);
    }

    public String value() {
        return this.value;
    }

    public String getValue() {
        return this.value;
    }

}
