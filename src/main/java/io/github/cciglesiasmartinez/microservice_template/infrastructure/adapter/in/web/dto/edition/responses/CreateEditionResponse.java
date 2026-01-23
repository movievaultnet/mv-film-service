package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.edition.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CreateEditionResponse {

    private String id;
    private boolean created;

}
