package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.item.responses;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.github.cciglesiasmartinez.microservice_template.domain.model.item.valueobjects.Condition;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(description = "Response containing an item")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GetItemResponse {

    @Schema(
            description = "Unique identifier of the item.",
            example = "ed-123e4567-e89b-12d3-a456-426614174000"
    )
    private String itemId;

    @Schema(
            description = "Unique identifier of the edition this item belongs to",
            example = "ed-123e4567-e89b-12d3-a456-426614174000"
    )
    private String editionId;

    @Schema(
            description = "Date when the item was purchased",
            example = "2024-12-15",
            type = "string",
            format = "date"
    )
    private LocalDate purchaseDate;

    @Schema(
            description = "Place or store where the item was purchased",
            example = "Amazon",
            maxLength = 255
    )
    private String purchasePlace;

    @Schema(
            description = "Purchase price of the item",
            example = "29.99",
            minimum = "0.01"
    )
    private Double purchasePrice;

    @Schema(
            description = "Condition of the physical media (disc, cartridge, etc.)",
            example = "NearMint",
            allowableValues = {"Mint", "NearMint", "VeryGoodPlus", "VeryGood", "GoodPlus", "Good", "Fair", "Poor"}
    )
    private Condition mediaCondition;

    @Schema(
            description = "Condition of the case or packaging",
            example = "VeryGood",
            allowableValues = {"Mint", "NearMint", "VeryGoodPlus", "VeryGood", "GoodPlus", "Good", "Fair", "Poor"}
    )
    private Condition caseCondition;

    @Schema(
            description = "Additional notes or comments about the item",
            example = "Limited edition with bonus content",
            maxLength = 5000
    )
    private String comments;

}
