package io.github.cciglesiasmartinez.microservice_template.application.port.in;

import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Envelope;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.ListGenericResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.elastic.ElasticEditionWrapper;

public interface ElasticUseCase {

    Envelope<ListGenericResponse<ElasticEditionWrapper>> searchEditions(String query, int page, int size);

}
