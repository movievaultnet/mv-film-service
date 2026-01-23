package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web;

import io.github.cciglesiasmartinez.microservice_template.application.port.in.ElasticUseCase;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Envelope;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.ListGenericResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.elastic.ElasticEditionWrapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/elastic")
@Tag(name = "Elastic service.", description = "Endpoints related to Elastic Search.")
public class ElasticController {

    private final ElasticUseCase elasticUseCase;

    // TODO: In the future extract this to a dedicated microservice
    @GetMapping("/search")
    public ResponseEntity<Envelope<ListGenericResponse<ElasticEditionWrapper>>> elasticEditionSearch(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        Envelope<ListGenericResponse<ElasticEditionWrapper>> response = elasticUseCase.searchEditions(query, page, size);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
