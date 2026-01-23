package io.github.cciglesiasmartinez.microservice_template.application.usecases.item;

import io.github.cciglesiasmartinez.microservice_template.domain.model.item.Item;
import io.github.cciglesiasmartinez.microservice_template.domain.model.item.valueobjects.Condition;
import io.github.cciglesiasmartinez.microservice_template.domain.model.item.valueobjects.ItemId;
import io.github.cciglesiasmartinez.microservice_template.domain.port.out.ItemRepository;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Envelope;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Meta;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.item.requests.UpdateItemRequest;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.item.responses.UpdateItemResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class UpdateItemUseCase {

    private ItemRepository itemRepository;

    private String updatePurchasePlaceIfPresent(UpdateItemRequest request, Item existing) {
        return request.getPurchasePlace() != null ? request.getPurchasePlace() : existing.purchasePlace();
    }

    private LocalDate updatePurchaseDateIfPresent(UpdateItemRequest request, Item existing) {
        return request.getPurchaseDate() != null ? request.getPurchaseDate() : existing.purchaseDate();
    }

    private Double updatePurchasePriceIfPresent(UpdateItemRequest request, Item existing) {
        return request.getPurchasePrice() != null ? request.getPurchasePrice() : existing.purchasePrice();
    }

    private Condition updateMediaConditionIfPresent(UpdateItemRequest request, Item existing) {
        return request.getMediaCondition() != null ? request.getMediaCondition() : existing.mediaCondition();
    }

    private Condition updateCaseConditionIfPresent(UpdateItemRequest request, Item existing) {
        return request.getCaseCondition() != null ? request.getCaseCondition() : existing.caseCondition();
    }

    private String updateCommentsIfPresent(UpdateItemRequest request, Item existing) {
        return request.getComments() != null ? request.getComments() : existing.comments();
    }

    private void checkIfUserIdIsValid(String userId, String itemUserId) {
        if (!userId.equals(itemUserId)) {
            throw new RuntimeException("User does not match");
        }
    }

    public Envelope<UpdateItemResponse> execute(UpdateItemRequest request, String userId) {
        ItemId itemId = ItemId.of(request.getId());
        Item existing = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Item ID not found."));
        checkIfUserIdIsValid(userId, existing.userId());
        Item updated = Item.of(
                existing.id(),
                existing.edition(),
                existing.userId(),
                updatePurchasePlaceIfPresent(request, existing),
                updatePurchaseDateIfPresent(request, existing),
                updatePurchasePriceIfPresent(request, existing),
                updateMediaConditionIfPresent(request, existing),
                updateCaseConditionIfPresent(request, existing),
                updateCommentsIfPresent(request, existing),
                existing.addedDate(),
                LocalDateTime.now()
        );
        itemRepository.update(updated);
        UpdateItemResponse data = new UpdateItemResponse(updated.id().value(), true);
        return new Envelope<>(data, new Meta());
    }

}