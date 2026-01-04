package io.github.cciglesiasmartinez.microservice_template.domain.model.valueobjects;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class FilmId {

    private final String value;

    private FilmId(String value) {
        this.value = value;
    }

    /** Deserializa desde JSON con clave "value" y valida formato UUID. */
    @JsonCreator
    private static FilmId fromJson(@JsonProperty("value") String value) {
        return of(value);
    }

    /** Crea un FilmId desde texto (no nulo/ vacío y con formato UUID válido). */
    public static FilmId of(String filmId) {
        if (filmId == null || filmId.isBlank()) {
            throw new IllegalArgumentException("FilmId can't be null or empty");
        }
        // Valida que sea un UUID válido
        UUID.fromString(filmId);
        return new FilmId(filmId);
    }

    /** Genera un nuevo identificador aleatorio. */
    public static FilmId generate() {
        return new FilmId(UUID.randomUUID().toString());
    }

    public String value() {
        return this.value;
    }

    public String getValue() {
        return this.value;
    }
}


