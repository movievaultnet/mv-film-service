package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.persistence.mysql.repository;

import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.item.wrappers.CollectionItemWrapper;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.persistence.mysql.entity.ItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SpringDataItemRepository extends JpaRepository<ItemEntity, String> {

    @Query("""
            SELECT new io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.item.wrappers.CollectionItemWrapper(
                i.id,
                e.id,
                e.coverPicture,
                f.title,
                e.releaseYear,
                e.country,
                e.format,
                e.packagingType,
                i.caseCondition,
                i.mediaCondition,
                i.comments,
                i.addedDate
            )
            FROM ItemEntity i
            JOIN i.edition e
            JOIN e.film f
            WHERE i.userId = :userId
            """)
    Page<CollectionItemWrapper> findAllByUserId(@Param("userId") String userId, Pageable pageable);

}
