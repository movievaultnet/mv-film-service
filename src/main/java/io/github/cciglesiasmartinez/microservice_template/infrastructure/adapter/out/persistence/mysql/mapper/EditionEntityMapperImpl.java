package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.persistence.mysql.mapper;

import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.Edition;
import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.Picture;
import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.valueobjects.*;
import io.github.cciglesiasmartinez.microservice_template.domain.model.film.Film;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.persistence.mysql.entity.EditionEntity;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.persistence.mysql.entity.FilmEntity;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.persistence.mysql.entity.PictureEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class EditionEntityMapperImpl implements EditionEntityMapper {

    private FilmEntityMapper filmEntityMapper;

    private Picture toDomain(PictureEntity entity) {
        return Picture.of(
                PictureId.of(entity.getId()),
                Url.of(entity.getUrl()),
                null,
                entity.getUploadedAt()
        );
    }

    private PictureEntity toEntity(Picture picture, EditionEntity editionEntity) {
        PictureEntity entity = new PictureEntity();
        entity.setId(picture.id().value());
        entity.setUrl(picture.url().value());
        entity.setUploadedAt(picture.uploadedAt());
        entity.setEdition(editionEntity);
        return entity;
    }

    private List<Picture> toDomainPictures(List<PictureEntity> entities) {
        return entities.stream()
                .map(this::toDomain)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private List<PictureEntity> toEntityPictures(List<Picture> pictures, EditionEntity editionEntity) {
        return pictures.stream()
                .map(p -> toEntity(p, editionEntity))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Edition toDomain(EditionEntity entity) {
        if (entity == null) return null;
        Film film = filmEntityMapper.toDomain(entity.getFilm());
        return Edition.of(
                EditionId.of(entity.getId()),
                film,
                Slug.of(entity.getSlug()),
                entity.getCoverPicture(),
                BarCode.of(entity.getBarCode()),
                Country.of(entity.getCountry()),
                entity.getFormat(), // 1:1 mapping from enum in VO and persistence
                entity.getReleaseYear(),
                entity.getPackagingType(), // 1:1
                Notes.of(entity.getNotes()),
                this.toDomainPictures(entity.getPictures())
        );
    }

    @Override
    public EditionEntity toEntity(Edition edition) {
        if (edition == null) return null;
        FilmEntity filmEntity = filmEntityMapper.toEntity(edition.film());
        EditionEntity entity = new EditionEntity();
        entity.setId(edition.editionId().value());
        entity.setFilm(filmEntity);
        entity.setSlug(edition.slug().value());
        entity.setCoverPicture(edition.coverPicture());
        entity.setBarCode(edition.barCode().value());
        entity.setCountry(edition.country().value());
        entity.setFormat(edition.format());
        entity.setReleaseYear(edition.releaseYear());
        entity.setPackagingType(edition.packagingType());
        entity.setNotes(edition.notes().value());
        List<PictureEntity> pictureEntities = this.toEntityPictures(edition.pictures(), entity);
        entity.setPictures(pictureEntities);
        return entity;
    }

    @Override
    public Edition updateEntity(EditionEntity entity, Edition edition) {

        entity.setSlug(edition.slug().value());
        entity.setBarCode(edition.barCode().value());
        entity.setCountry(edition.country().value());
        entity.setFormat(edition.format());
        entity.setReleaseYear(edition.releaseYear());
        entity.setPackagingType(edition.packagingType());
        entity.setCoverPicture(edition.coverPicture());
        entity.setNotes(edition.notes().value());
        entity.setUpdatedAt(LocalDateTime.now());

        // Index for existing pics
        Map<String, PictureEntity> managedPictures = entity.getPictures().stream()
                        .collect(Collectors.toMap(PictureEntity::getId, Function.identity()));
        // Clean those that does not exist in domain
        entity.getPictures().removeIf(pe -> edition.pictures().stream()
                .noneMatch(p -> p.id().value().equals(pe.getId())));
        // Update
        for (Picture p: edition.pictures()) {
            PictureEntity pe = managedPictures.get(p.id().value());
            if (pe == null) {
                pe = new PictureEntity();
                pe.setId(p.id().value());
                pe.setEdition(entity);
                entity.getPictures().add(pe);
            }
            pe.setUrl(p.url().value());
            pe.setUploadedAt(p.uploadedAt());
        }
        return toDomain(entity);
    }
}
