package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.persistence.mysql.repository;

import java.util.Optional;

import io.github.cciglesiasmartinez.microservice_template.domain.model.film.valueobjects.TmdbId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import io.github.cciglesiasmartinez.microservice_template.domain.model.film.Film;
import io.github.cciglesiasmartinez.microservice_template.domain.model.film.valueobjects.FilmId;
import io.github.cciglesiasmartinez.microservice_template.domain.port.out.FilmRepository;
import io.github.cciglesiasmartinez.microservice_template.domain.shared.PageResult;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.persistence.mysql.entity.FilmEntity;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.persistence.mysql.mapper.FilmEntityMapper;
import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
@Transactional(readOnly = true)
public class FilmRepositoryImpl implements FilmRepository {

    private final SpringDataFilmRepository springDataFilmRepository;
    private final FilmEntityMapper mapper;

    @Override
    @Transactional
    public Film create(Film film) {
        FilmEntity saved = springDataFilmRepository.save(mapper.toEntity(film));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Film> findById(FilmId id) {
        return springDataFilmRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    @Transactional
    public Film update(Film film) {
        FilmEntity saved = springDataFilmRepository.save(mapper.toEntity(film));
        return mapper.toDomain(saved);
    }

    @Override
    @Transactional
    public void delete(Film film) {
        springDataFilmRepository.delete(mapper.toEntity(film));
    }

    @Override
    @Transactional
    public boolean deleteById(FilmId id) {
        if (!springDataFilmRepository.existsById(id.value())) return false;
        springDataFilmRepository.deleteById(id.value());
        return true;
    }

    @Override
    @Transactional
    public Film save(Film film) {
        FilmEntity saved = springDataFilmRepository.save(mapper.toEntity(film));
        return mapper.toDomain(saved);
    }

    @Override
    public PageResult<Film> findPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<FilmEntity> p = springDataFilmRepository.findAll(pageable);

        var content = p.getContent().stream().map(mapper::toDomain).toList();

        return new PageResult<>(
            content,
            p.getNumber(),
            p.getSize(),
            p.getTotalElements(),
            p.getTotalPages(),
            p.hasNext(),
            p.hasPrevious()
        );
    }

    @Override
    public Optional<Film> findByTmdbId(TmdbId tmdbId) {
        return springDataFilmRepository.findByTmdbId(tmdbId.value()).map(mapper::toDomain);
    }
}

