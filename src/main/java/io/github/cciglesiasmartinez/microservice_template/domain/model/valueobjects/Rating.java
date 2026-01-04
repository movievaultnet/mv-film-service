package io.github.cciglesiasmartinez.microservice_template.domain.model.valueobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class Rating {

    private final String value;

    @JsonCreator
    private Rating(@JsonProperty("value") String value) {
        this.value = value;
    }

    public static Rating of(String rating) {
        if (rating == null || rating.isBlank()) {
            throw new IllegalArgumentException("Rating can't be null or empty.");
        }
        return new Rating(rating);
    }

    public String value() {
        return this.value;
    }

    public String getValue() {
        return this.value;
    }

}
