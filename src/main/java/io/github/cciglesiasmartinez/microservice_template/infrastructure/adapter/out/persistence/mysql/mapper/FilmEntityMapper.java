package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.persistence.mysql.mapper;

import io.github.cciglesiasmartinez.microservice_template.domain.model.Film;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.persistence.mysql.entity.FilmEntity;

public interface FilmEntityMapper {

    Film toDomain(FilmEntity entity);

    FilmEntity toEntity(Film film);
}