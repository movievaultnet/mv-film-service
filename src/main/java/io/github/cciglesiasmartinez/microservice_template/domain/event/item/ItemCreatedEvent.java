package io.github.cciglesiasmartinez.microservice_template.domain.event.item;

import io.github.cciglesiasmartinez.microservice_template.domain.event.DomainEvent;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class ItemCreatedEvent extends DomainEvent {

    private final String id;
    private final String editionId;
    private final String userId;
    private final LocalDate purchaseDate;
    private final String purchasePlace;
    private final Double purchasePrice;
    private final String mediaCondition;
    private final String caseCondition;
    private final String comments;
    private final LocalDateTime addedAt;

    @Builder
    private ItemCreatedEvent(String id,
                             String editionId,
                             String userId,
                             LocalDate purchaseDate,
                             String purchasePlace,
                             Double purchasePrice,
                             String mediaCondition,
                             String caseCondition,
                             String comments,
                             LocalDateTime addedAt) {
        super(ItemCreatedEvent.class.getSimpleName());
        this.id = id;
        this.editionId = editionId;
        this.userId = userId;
        this.purchaseDate = purchaseDate;
        this.purchasePlace = purchasePlace;
        this.purchasePrice = purchasePrice;
        this.mediaCondition = mediaCondition;
        this.caseCondition = caseCondition;
        this.comments = comments;
        this.addedAt = addedAt;
    }

}
