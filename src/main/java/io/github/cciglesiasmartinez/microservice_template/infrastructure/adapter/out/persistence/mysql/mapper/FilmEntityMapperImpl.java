package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.persistence.mysql.mapper;

import org.springframework.stereotype.Component;

import io.github.cciglesiasmartinez.microservice_template.domain.model.Film;
import io.github.cciglesiasmartinez.microservice_template.domain.model.valueobjects.*;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.persistence.mysql.entity.FilmEntity;

@Component
public final class FilmEntityMapperImpl implements FilmEntityMapper {

    @Override
    public Film toDomain(FilmEntity entity) {
        if (entity == null) return null;

        return Film.of(
            FilmId.of(entity.getId()),
            Title.of(entity.getTitle()),
            entity.getDescription() != null ? Description.of(entity.getDescription()) : null,
            ReleaseYear.of(entity.getReleaseYear()),
            ProducingCountry.of(entity.getProducingCountry()),
            Rating.of(entity.getRating()),
            entity.getPoster() != null ? Poster.of(entity.getPoster()) : null
        );
    }

    @Override
    public FilmEntity toEntity(Film film) {
        if (film == null) return null;

        FilmEntity e = new FilmEntity();
        e.setId(film.itemId().getValue());
        e.setTitle(film.title().value());
        e.setDescription(film.description() != null ? film.description().value() : null);
        e.setReleaseYear(film.releaseYear().value());
        e.setProducingCountry(film.producingCountry().value());
        e.setRating(film.rating().value());
        e.setPoster(film.poster() != null ? film.poster().value() : null);
        return e;
    }
}
