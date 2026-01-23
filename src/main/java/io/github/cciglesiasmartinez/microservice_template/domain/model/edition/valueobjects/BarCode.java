package io.github.cciglesiasmartinez.microservice_template.domain.model.edition.valueobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BarCode {

    private final String value;

    @JsonCreator
    private BarCode(@JsonProperty("value") String value) {
        this.value = value;
    }

    public static BarCode of(String barCode) {
        if ((barCode == null) || barCode.isBlank()) {
            throw new IllegalArgumentException("Bar code can't be null nor empty.");
        }
        return new BarCode(barCode);
    }

    public String value() {
        return this.value;
    }

    public String getValue() {
        return this.value;
    }

}
