package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.persistence.mysql.mapper;

import io.github.cciglesiasmartinez.microservice_template.domain.model.item.Item;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.persistence.mysql.entity.ItemEntity;

public interface ItemEntityMapper {

    Item toDomain(ItemEntity entity);
    ItemEntity toEntity(Item item);
    void updateEntity(ItemEntity entity, Item item);

}
