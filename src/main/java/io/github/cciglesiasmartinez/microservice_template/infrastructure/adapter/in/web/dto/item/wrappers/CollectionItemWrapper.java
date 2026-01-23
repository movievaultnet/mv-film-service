package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.item.wrappers;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.valueobjects.Format;
import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.valueobjects.PackagingType;
import io.github.cciglesiasmartinez.microservice_template.domain.model.item.valueobjects.Condition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.Year;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CollectionItemWrapper {

    private String id; // Item
    private String editionId; // Item
    private String editionCoverPicture; // Edition
    private String filmName; // Film
    private Year editionReleaseYear; // Edition
    private String editionCountry; // Edition
    private Format editionFormat; // Edition
    private PackagingType editionPackaging; // Edition
    private Condition itemCaseCondition; // Item
    private Condition itemMediaCondition; // Item
    private String itemComments; // Item
    private LocalDateTime itemAddedDate; // Item

}
