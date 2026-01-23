package io.github.cciglesiasmartinez.microservice_template.domain.model.film.valueobjects;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class TmdbId {

    private final Long value;

    private TmdbId(Long value) {
        this.value = value;
    }

    public static TmdbId of(Long tmdbId) {
        if (tmdbId == null) {
            throw new IllegalArgumentException("TMDB ID can't be null.");
        }
        return new TmdbId(tmdbId);
    }

    public long getValue() {
        return this.value;
    }

    public long value() {
        return this.value;
    }
}
