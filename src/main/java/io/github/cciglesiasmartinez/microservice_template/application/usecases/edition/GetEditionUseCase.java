package io.github.cciglesiasmartinez.microservice_template.application.usecases.edition;

import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.Edition;
import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.valueobjects.EditionId;
import io.github.cciglesiasmartinez.microservice_template.domain.port.out.EditionRepository;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Envelope;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Meta;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.edition.responses.GetEditionResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Get edition use case.
 */
@Service
@AllArgsConstructor
@Slf4j
public class GetEditionUseCase {

    private final EditionRepository editionRepository;

    /**
     * Executes get edition use case.
     *
     * @param id
     * @return
     */
    public Envelope<GetEditionResponse> execute(String id) {
        EditionId editionId = EditionId.of(id);
        Edition edition = editionRepository.findById(editionId)
                .orElseThrow(() -> {
                    log.warn("Edition ID does not exist.");
                    return new IllegalArgumentException("Edition ID does not exist.");
                });
        GetEditionResponse data = GetEditionResponse.from(edition);
        return new Envelope<>(data, new Meta());
    }

}
