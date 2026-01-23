package io.github.cciglesiasmartinez.microservice_template.application.usecases.item;

import io.github.cciglesiasmartinez.microservice_template.domain.model.item.Item;
import io.github.cciglesiasmartinez.microservice_template.domain.model.item.valueobjects.ItemId;
import io.github.cciglesiasmartinez.microservice_template.domain.port.out.ItemRepository;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Envelope;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Meta;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.item.responses.DeleteItemResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class DeleteItemUseCase {

    private final ItemRepository itemRepository;

    private Item getItemFrom(ItemId itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found."));
    }

    private void checkIfUserIsValid(String userIdFromItem, String userId) {
        if (!userIdFromItem.equals(userId)) {
            throw new RuntimeException("User does not match.");
        }
    }

    public Envelope<DeleteItemResponse> execute(String id, String userId) {
        ItemId itemId = ItemId.of(id);
        Item item = getItemFrom(itemId);
        checkIfUserIsValid(item.id().value(), userId);
        itemRepository.deleteById(itemId);
        DeleteItemResponse data = new DeleteItemResponse(itemId.value(), true);
        log.info("Item {} deleted", itemId.value());
        return new Envelope<>(data, new Meta());
    }

}
