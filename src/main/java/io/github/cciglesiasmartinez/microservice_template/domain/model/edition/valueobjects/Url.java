package io.github.cciglesiasmartinez.microservice_template.domain.model.edition.valueobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class Url {

    private final String value;

    @JsonCreator
    private Url(@JsonProperty("value") String value) {
        this.value = value;
    }

    public static Url of(String url) {
        if ((url == null) || url.isBlank()) {
            throw new IllegalArgumentException("URL can't be null nor empty.");
        }
        return new Url(url);
    }

    public String value() {
        return this.value;
    }

    public String getValue() {
        return this.value;
    }

}
