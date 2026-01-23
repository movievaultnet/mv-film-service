package io.github.cciglesiasmartinez.microservice_template.domain.port.out;

import java.util.Optional;

import io.github.cciglesiasmartinez.microservice_template.domain.model.film.Film;
import io.github.cciglesiasmartinez.microservice_template.domain.model.film.valueobjects.FilmId;
import io.github.cciglesiasmartinez.microservice_template.domain.model.film.valueobjects.TmdbId;
import io.github.cciglesiasmartinez.microservice_template.domain.shared.PageResult;

public interface FilmRepository {
	
	// CREATE
    Film create(Film film);

    // READ
    Optional<Film> findById(FilmId id);
    PageResult<Film> findPage(int page, int size);
    Optional<Film> findByTmdbId(TmdbId tmdbId);

    // UPDATE
    Film update(Film film); // lanza si no existe

    // DELETE
    void delete(Film film);
    boolean deleteById(FilmId id);

	Film save(Film film);
	


}
