package io.github.cciglesiasmartinez.microservice_template.application.usecases.edition;

import io.github.cciglesiasmartinez.microservice_template.domain.event.DomainEventPublisher;
import io.github.cciglesiasmartinez.microservice_template.domain.event.edition.EditionUpdatedEvent;
import io.github.cciglesiasmartinez.microservice_template.domain.exception.WrongFilmIdException;
import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.Edition;
import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.valueobjects.*;
import io.github.cciglesiasmartinez.microservice_template.domain.port.out.EditionRepository;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Envelope;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Meta;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.edition.requests.UpdateEditionRequest;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.edition.responses.UpdateEditionResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Year;

/**
 * Update edition use case.
 * TODO: Consider updating the full aggregate instead of having separate use cases for pictures.
 */
@Service
@AllArgsConstructor
@Slf4j
public class UpdateEditionUseCase {

    private EditionRepository editionRepository;
    private DomainEventPublisher domainEventPublisher;

    private Edition getEditionFromRepository(UpdateEditionRequest request) {
        return editionRepository.findById(EditionId.of(request.getId()))
                .orElseThrow(() -> new WrongFilmIdException("Film ID not found"));
    }

    private String updateCoverPictureIfPresent(UpdateEditionRequest request, Edition existing) {
        return request.getCoverPicture() != null ? request.getCoverPicture() : existing.coverPicture();
    }

    private Notes updateNotesIfPresent(UpdateEditionRequest request, Edition existing) {
        return request.getNotes() != null ?  Notes.of(request.getNotes()) : existing.notes();
    }

    private PackagingType updatePackagingIfPresent(UpdateEditionRequest request, Edition existing) {
        return request.getPackagingType() != null
                ? PackagingType.valueOf(request.getPackagingType()) : existing.packagingType();
    }

    private Year updateYearIfPresent(UpdateEditionRequest request, Edition existing) {
        return request.getReleaseYear() != null ? request.getReleaseYear() : existing.releaseYear();
    }

    private Format updateFormatIfPresent(UpdateEditionRequest request, Edition existing) {
        return request.getFormat() != null ? Format.valueOf(request.getFormat()) : existing.format();
    }

    private Country updateCountryIfPresent(UpdateEditionRequest request, Edition existing) {
        return request.getCountry() != null ? Country.of(request.getCountry()) : existing.country();
    }

    private BarCode updateBarCodeIfPresent(UpdateEditionRequest request, Edition existing) {
        return  request.getBarCode() != null ? BarCode.of(request.getBarCode()) : existing.barCode();
    }

    private EditionUpdatedEvent buildEditionUpdatedEvent(Edition updated) {
        EditionUpdatedEvent event = EditionUpdatedEvent.builder()
                .editionId(updated.editionId().value())
                .filmId(updated.film().id().value())
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

    public Envelope<UpdateEditionResponse> execute(UpdateEditionRequest request) {
        Edition existing = getEditionFromRepository(request);
        Edition updated = Edition.of(
                existing.editionId(),
                existing.film(),
                existing.slug(), // TODO: Consider an update "factory" method for these cases
                updateCoverPictureIfPresent(request, existing),
                updateBarCodeIfPresent(request, existing),
                updateCountryIfPresent(request, existing),
                updateFormatIfPresent(request, existing),
                updateYearIfPresent(request, existing),
                updatePackagingIfPresent(request, existing),
                updateNotesIfPresent(request, existing),
                existing.pictures());
        EditionUpdatedEvent event = buildEditionUpdatedEvent(updated);
        domainEventPublisher.publish(event);
        updated = editionRepository.update(updated); // TODO: Review this, consider a void method instead.
        UpdateEditionResponse data = new UpdateEditionResponse(updated.editionId().value(), true);
        return new Envelope<>(data, new Meta());
    }

}
