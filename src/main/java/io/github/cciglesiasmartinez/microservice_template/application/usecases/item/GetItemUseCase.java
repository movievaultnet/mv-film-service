package io.github.cciglesiasmartinez.microservice_template.application.usecases.item;

import io.github.cciglesiasmartinez.microservice_template.domain.model.item.Item;
import io.github.cciglesiasmartinez.microservice_template.domain.model.item.valueobjects.ItemId;
import io.github.cciglesiasmartinez.microservice_template.domain.port.out.ItemRepository;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Envelope;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Meta;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.item.responses.GetItemResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Slf4j
public class GetItemUseCase {

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

    public Envelope<GetItemResponse> execute(String id, String userId) {
        ItemId itemId = ItemId.of(id);
        Item item = getItemFrom(itemId);
        checkIfUserIsValid(item.userId(), userId);
        GetItemResponse data = new GetItemResponse(
                item.id().value(),
                item.edition().editionId().value(),
                item.purchaseDate(),
                item.purchasePlace(),
                item.purchasePrice(),
                item.mediaCondition(),
                item.caseCondition(),
                item.comments()
        );
        return new Envelope<>(data, new Meta());
    }

}
