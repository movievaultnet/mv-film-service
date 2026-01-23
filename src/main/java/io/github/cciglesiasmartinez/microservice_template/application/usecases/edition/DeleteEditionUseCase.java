package io.github.cciglesiasmartinez.microservice_template.application.usecases.edition;

import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.valueobjects.EditionId;
import io.github.cciglesiasmartinez.microservice_template.domain.port.out.EditionRepository;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Envelope;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Meta;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.edition.responses.DeleteEditionResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class DeleteEditionUseCase {

    private EditionRepository editionRepository;

    /**
     * Executes delete edition use case.
     *
     * @param id
     * @return
     */
    public Envelope<DeleteEditionResponse> execute (String id) {
        EditionId editionId = EditionId.of(id);
        if (!editionRepository.deleteById(editionId)) throw new RuntimeException("ID not found");
        DeleteEditionResponse data = new DeleteEditionResponse(editionId.value(), true);
        return new Envelope<>(data, new Meta());
    }

}
