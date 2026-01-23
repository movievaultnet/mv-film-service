package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.item.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateItemResponse {

    private String id;
    private boolean created;

}
