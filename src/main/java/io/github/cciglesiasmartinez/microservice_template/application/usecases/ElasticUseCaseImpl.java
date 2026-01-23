package io.github.cciglesiasmartinez.microservice_template.application.usecases;

import io.github.cciglesiasmartinez.microservice_template.application.port.in.ElasticUseCase;
import io.github.cciglesiasmartinez.microservice_template.application.usecases.elastic.SearchEditionsUseCase;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Envelope;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.ListGenericResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.elastic.ElasticEditionWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ElasticUseCaseImpl implements ElasticUseCase {

    private final SearchEditionsUseCase searchEditionsUseCase;

    @Override
    public Envelope<ListGenericResponse<ElasticEditionWrapper>> searchEditions(String query, int page, int size) {
        return searchEditionsUseCase.execute(query, page, size);
    }
}
