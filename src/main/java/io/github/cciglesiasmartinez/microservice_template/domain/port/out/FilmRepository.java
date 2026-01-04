package io.github.cciglesiasmartinez.microservice_template.domain.port.out;

import java.util.List;
import java.util.Optional;

import io.github.cciglesiasmartinez.microservice_template.domain.model.Film;
import io.github.cciglesiasmartinez.microservice_template.domain.model.valueobjects.FilmId;
import io.github.cciglesiasmartinez.microservice_template.domain.model.valueobjects.Title;
import io.github.cciglesiasmartinez.microservice_template.domain.shared.PageResult;

public interface FilmRepository {
	
	// CREATE
    Film create(Film film);

    // READ
    Optional<Film> findById(FilmId id);
    List<Film> findAll();
    Optional<Film> findByTitle(Title title); // opcional

    boolean existsById(FilmId id);
    boolean existsByTitle(Title title);      // opcional

    // UPDATE
    Film update(Film film); // lanza si no existe

    // DELETE
    void delete(Film film);
    boolean deleteById(FilmId id);

	Film save(Film film);
	
	PageResult<Film> findPage(int page, int size);

}
