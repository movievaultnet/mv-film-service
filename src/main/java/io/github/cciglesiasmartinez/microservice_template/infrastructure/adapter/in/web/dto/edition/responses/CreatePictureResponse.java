package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.edition.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class CreatePictureResponse {

    private String id;
    private boolean created;

}
