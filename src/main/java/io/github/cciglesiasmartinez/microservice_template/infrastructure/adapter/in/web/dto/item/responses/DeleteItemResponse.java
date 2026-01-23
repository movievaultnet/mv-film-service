package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.item.responses;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "Response after deleting an item.")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DeleteItemResponse {

    @Schema(
            description = "Unique identifier of the item",
            example = "ed-123e4567-e89b-12d3-a456-426614174000"
    )
    private String id;

    @Schema(
            description = "Boolean message indicating success or failure",
            example = "true"
    )
    private boolean deleted;

}
