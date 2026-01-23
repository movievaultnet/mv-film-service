package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.edition.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
public class DeleteEditionResponse {

    @Schema(description = "Edition identifier", example = "9c5e0d8f-53d7-4b1e-9b97-3e8f1f2b0a3e")
    private String id;

    @Schema(description = "Deletion status", example = "true")
    private boolean deleted;

}
