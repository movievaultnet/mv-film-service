package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.persistence.mysql.mapper;

import io.github.cciglesiasmartinez.microservice_template.domain.model.item.Item;
import io.github.cciglesiasmartinez.microservice_template.domain.model.item.valueobjects.ItemId;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.persistence.mysql.entity.ItemEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class ItemEntityMapperImpl implements ItemEntityMapper{

    private EditionEntityMapper editionEntityMapper;

    @Override
    public Item toDomain(ItemEntity entity) {
        return Item.of(
                ItemId.of(entity.getId()),
                editionEntityMapper.toDomain(entity.getEdition()),
                entity.getUserId(),
                entity.getPurchasePlace(),
                entity.getPurchaseDate(),
                entity.getPurchasePrice(),
                entity.getCaseCondition(),
                entity.getMediaCondition(),
                entity.getComments(),
                entity.getAddedDate(),
                entity.getLastModified()
        );
    }

    @Override
    public ItemEntity toEntity(Item item) {
        ItemEntity entity = new ItemEntity();
        entity.setId(item.id().value());
        entity.setEdition(editionEntityMapper.toEntity(item.edition()));
        entity.setUserId(item.userId());
        entity.setPurchasePlace(item.purchasePlace());
        entity.setPurchaseDate(item.purchaseDate());
        entity.setPurchasePrice(item.purchasePrice());
        entity.setCaseCondition(item.caseCondition());
        entity.setMediaCondition(item.mediaCondition());
        entity.setComments(item.comments());
        entity.setAddedDate(item.addedDate());
        entity.setLastModified(item.lastModified());
        return entity;
    }

    @Override
    public void updateEntity(ItemEntity entity, Item item) {
        ItemEntity updated = toEntity(item);
        entity.setPurchaseDate(updated.getPurchaseDate());
        entity.setPurchasePlace(updated.getPurchasePlace());
        entity.setPurchasePrice(updated.getPurchasePrice());
        entity.setMediaCondition(updated.getMediaCondition());
        entity.setCaseCondition(updated.getCaseCondition());
        entity.setComments(updated.getComments());
        entity.setLastModified(updated.getLastModified());
    }

}
