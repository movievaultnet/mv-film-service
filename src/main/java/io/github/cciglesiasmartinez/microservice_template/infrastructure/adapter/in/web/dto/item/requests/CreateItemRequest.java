package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.item.requests;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.github.cciglesiasmartinez.microservice_template.domain.model.item.valueobjects.Condition;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Request payload for creating a new item in a user's collection")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreateItemRequest {

    @Schema(
            description = "Unique identifier of the edition this item belongs to",
            example = "ed-123e4567-e89b-12d3-a456-426614174000"
    )
    @NotBlank(message = "Edition ID is required")
    private String editionId;

    @Schema(
            description = "Date when the item was purchased",
            example = "2024-12-15",
            type = "string",
            format = "date"
    )
    @PastOrPresent(message = "Purchase date cannot be in the future")
    private LocalDate purchaseDate;

    @Schema(
            description = "Place or store where the item was purchased",
            example = "Amazon",
            maxLength = 255
    )
    @Size(max = 255, message = "Purchase place must not exceed 255 characters")
    private String purchasePlace;

    @Schema(
            description = "Purchase price of the item",
            example = "29.99",
            minimum = "0.01"
    )
    @Positive(message = "Purchase price must be positive")
    @DecimalMin(value = "0.01", message = "Purchase price must be at least 0.01")
    private Double purchasePrice;

    @Schema(
            description = "Condition of the physical media (disc, cartridge, etc.)",
            example = "NearMint",
            allowableValues = {"Mint", "NearMint", "VeryGoodPlus", "VeryGood", "GoodPlus", "Good", "Fair", "Poor"}
    )
    @NotNull(message = "Media condition is required")
    private Condition mediaCondition;

    @Schema(
            description = "Condition of the case or packaging",
            example = "VeryGood",
            allowableValues = {"Mint", "NearMint", "VeryGoodPlus", "VeryGood", "GoodPlus", "Good", "Fair", "Poor"}
    )
    @NotNull(message = "Case condition is required")
    private Condition caseCondition;

    @Schema(
            description = "Additional notes or comments about the item",
            example = "Limited edition with bonus content",
            maxLength = 5000
    )
    @Size(max = 5000, message = "Comments must not exceed 5000 characters")
    private String comments;

}
