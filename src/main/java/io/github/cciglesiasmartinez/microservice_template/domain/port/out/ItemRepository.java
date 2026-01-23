package io.github.cciglesiasmartinez.microservice_template.domain.port.out;

import io.github.cciglesiasmartinez.microservice_template.domain.model.item.Item;
import io.github.cciglesiasmartinez.microservice_template.domain.model.item.valueobjects.ItemId;
import io.github.cciglesiasmartinez.microservice_template.domain.shared.PageResult;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.item.wrappers.CollectionItemWrapper;

import java.util.List;
import java.util.Optional;

public interface ItemRepository {

    // Create
    Item create(Item item);

    // Read
    Optional<Item> findById(ItemId itemId);
    List<Item> findAll();
    List<Item> findAllByUserId(String userId);
    PageResult<CollectionItemWrapper> findPageByUserId(int page, int size, String userId);
    PageResult<Item> findPage(int page, int size);

    // Update
    void update(Item item);

    // Delete
    void deleteById(ItemId id);

}
