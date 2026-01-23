package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.tmdb.requests;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TmdbSearchRequest {

    private String query;
    private Boolean includeAdult;
    private String language;
    private String primaryReleaseYear;
    private int page;
    private String region;
    private String year;

}
