package io.github.cciglesiasmartinez.microservice_template.application.usecases.edition;

import io.github.cciglesiasmartinez.microservice_template.domain.event.DomainEventPublisher;
import io.github.cciglesiasmartinez.microservice_template.domain.event.edition.EditionUpdatedEvent;
import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.Edition;
import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.Picture;
import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.valueobjects.*;
import io.github.cciglesiasmartinez.microservice_template.domain.port.out.EditionRepository;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Envelope;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Meta;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.edition.responses.SetEditionCoverResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class SetEditionCoverUseCase {

    private final EditionRepository editionRepository;
    private DomainEventPublisher domainEventPublisher;

    private EditionUpdatedEvent buildEditionUpdatedEvent(Edition updated) {
        EditionUpdatedEvent event = EditionUpdatedEvent.builder()
                .editionId(updated.editionId().value())
                .filmId(updated.film().id().value())
                .filmTitle(updated.film().title().value())
                .filmSummary(updated.film().description().value())
                .slug(updated.slug().value())
                .coverPicture(updated.coverPicture())
                .barCode(updated.barCode().value())
                .country(updated.country().value())
                .format(updated.format().name())
                .releaseYear(updated.releaseYear())
                .packagingType(updated.packagingType().name())
                .notes(updated.notes().value())
                .build();
        return event;
    }

    private Edition getEditionFrom(EditionId id) {
        return editionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Edition ID not found."));
    }

    private boolean setCoverPicture(Edition edition, PictureId pId) {
        for (Picture p: edition.pictures()) {
            if (p.id().value().equals(pId.value())) {
                edition.setCoverPicture(pId);
                return true;
            }
        }
        return false;
    }

    public Envelope<SetEditionCoverResponse> execute(String editionId, String pictureId) {
        EditionId targetId = EditionId.of(editionId);
        Edition edition = getEditionFrom(targetId);
        PictureId targetPicture = PictureId.of(pictureId);
        if (!setCoverPicture(edition, targetPicture))
            throw new RuntimeException("Picture ID not found");
        Edition updated = editionRepository.update(edition);
        log.info("!!! TENEMOS ESTA EDITION PVTA {}", updated.toString());
        EditionUpdatedEvent event = buildEditionUpdatedEvent(updated);
        log.info("!!! PUBLICAMOS ESTE EVENTO PERRA {}", event.toString());
        domainEventPublisher.publish(event);
        SetEditionCoverResponse data = new SetEditionCoverResponse(edition.coverPicture(), true);
        return new Envelope<>(data, new Meta());
    }

}
