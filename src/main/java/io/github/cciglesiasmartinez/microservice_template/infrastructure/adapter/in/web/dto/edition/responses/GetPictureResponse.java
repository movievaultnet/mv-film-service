package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.edition.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(description = "Picture resource.")
public class GetPictureResponse {

    private String id;
    private String url;
    private LocalDateTime uploadedAt;

}
