package io.github.cciglesiasmartinez.microservice_template.domain.model.item.valueobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.UUID;

@EqualsAndHashCode
@ToString
public class ItemId {

    private final String value;

    private ItemId(String value) {
        this.value = value;
    }

    @JsonCreator
    private static ItemId fromJson(@JsonProperty("value") String value) {
        return of(value);
    }

    public static ItemId of(String itemId) {
        if (itemId == null || itemId.isBlank())
            throw new IllegalArgumentException("ItemId can't be null or empty.");
        UUID.fromString(itemId);
        return new ItemId(itemId);
    }

    public static ItemId generate() {
        return new ItemId(UUID.randomUUID().toString());
    }

    public String value() {
        return this.value;
    }

    public String getValue() {
        return this.value;
    }

}
