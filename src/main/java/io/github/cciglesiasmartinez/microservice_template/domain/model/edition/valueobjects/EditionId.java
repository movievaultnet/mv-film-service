package io.github.cciglesiasmartinez.microservice_template.domain.model.edition.valueobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.UUID;

@EqualsAndHashCode
@ToString
public class EditionId {

    private final String value;

    private EditionId(String value) {
        this.value = value;
    }

    @JsonCreator
    private static EditionId fromJson(@JsonProperty("value") String value) { return of(value); }

    /**
     * Creates a EditionId instance from a {@link String}. Used to bring a EditionId from a persistence object.
     *
     * @param editionId object id in {@link String} format.
     * @return an EditionId instance.
     */
    public static EditionId of(String editionId) {
        if (editionId == null || editionId.isBlank()) {
            throw new IllegalArgumentException("EditionId can't be null or empty.");
        }
        UUID.fromString(editionId); // Check if it's a valid UUID.
        return new EditionId(editionId);
    }

    /**
     * Generates a new EditionId using a random {@link UUID} value.
     *
     * @return a new EditionId instance.
     */
    public static EditionId generate() {
        return new EditionId(UUID.randomUUID().toString());
    }

    public String value() {
        return this.value;
    }

    public String getValue() {
        return this.value;
    }
}
