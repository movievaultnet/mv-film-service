package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.edition.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateEditionRequest {

    private String filmId;
    private String barCode;
    private String country;
    private String format;
    private int releaseYear;
    private String packagingType;
    private String notes;

}
