package io.github.cciglesiasmartinez.microservice_template.domain.model.edition.valueobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.UUID;

@EqualsAndHashCode
@ToString
public class PictureId {

    private final String value;

    private PictureId(String value) {
        this.value = value;
    }

    @JsonCreator
    private static PictureId fromJson(@JsonProperty("value") String value) { return of(value); }

    /**
     * Creates a PictureId instance from a {@link String}. Used to bring a PictureId from a persistence object.
     *
     * @param pictureId object id in {@link String} format.
     * @return an PictureId instance.
     */
    public static PictureId of(String pictureId) {
        if (pictureId == null || pictureId.isBlank()) {
            throw new IllegalArgumentException("PictureId can't be null or empty.");
        }
        UUID.fromString(pictureId); // Check if it's a valid UUID.
        return new PictureId(pictureId);
    }

    /**
     * Generates a new PictureId using a random {@link UUID} value.
     *
     * @return a new PictureId instance.
     */
    public static PictureId generate() {
        return new PictureId(UUID.randomUUID().toString());
    }

    public String value() {
        return this.value;
    }

    public String getValue() {
        return this.value;
    }
}
