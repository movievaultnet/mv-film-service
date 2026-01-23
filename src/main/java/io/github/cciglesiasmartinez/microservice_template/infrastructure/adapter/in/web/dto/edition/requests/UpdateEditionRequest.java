package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.edition.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Year;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateEditionRequest {

    private String id;
    private String coverPicture;
    private String barCode;
    private String country;
    private String format;
    private Year releaseYear;
    private String packagingType;
    private String notes;

}
