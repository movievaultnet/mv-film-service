package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.edition.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreatePictureRequest {

    private String url;
    private String type;

}
