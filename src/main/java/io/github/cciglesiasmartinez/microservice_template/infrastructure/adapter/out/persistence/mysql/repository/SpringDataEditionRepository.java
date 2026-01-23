package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.persistence.mysql.repository;

import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.persistence.mysql.entity.EditionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SpringDataEditionRepository extends JpaRepository<EditionEntity, String> {

    boolean existsByBarCode(String barCode);
    Optional<EditionEntity> findByBarCode(String barCode);

    @Query("SELECT e FROM EditionEntity e JOIN FETCH e.film f LEFT JOIN FETCH e.pictures p WHERE e.id = :id")
    Optional<EditionEntity> findById(String id);

    @EntityGraph(attributePaths = {"film", "pictures"})
    Page<EditionEntity> findAll(Pageable page);

}
