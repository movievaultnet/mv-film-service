package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.persistence.mysql.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.persistence.mysql.entity.FilmEntity;

public interface SpringDataFilmRepository extends JpaRepository<FilmEntity, String> {

    boolean existsByTitle(String title);
    Optional<FilmEntity> findByTitle(String title);
    Optional<FilmEntity> findByTitleIgnoreCase(String title);
    List<FilmEntity> findAllByTitleContainingIgnoreCase(String title);
    
}
