package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.persistence.mysql.mapper;

import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.Edition;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.persistence.mysql.entity.EditionEntity;

public interface EditionEntityMapper {

    Edition toDomain(EditionEntity entity);
    EditionEntity toEntity(Edition edition);
    Edition updateEntity(EditionEntity entity, Edition edition);

}
