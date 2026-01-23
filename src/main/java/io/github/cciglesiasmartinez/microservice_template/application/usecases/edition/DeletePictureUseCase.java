package io.github.cciglesiasmartinez.microservice_template.application.usecases.edition;

import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.Edition;
import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.Picture;
import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.valueobjects.EditionId;
import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.valueobjects.Slug;
import io.github.cciglesiasmartinez.microservice_template.domain.port.out.EditionRepository;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Envelope;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Meta;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.edition.responses.DeletePictureResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.storage.localstorage.StorageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class DeletePictureUseCase {

    private EditionRepository editionRepository;
    private StorageService storageService;

    private void removePicture(String pictureId, Edition edition) {
        Slug editionSlug = edition.slug();
        Picture pictureToDelete = null;
        for (Picture p: edition.pictures()) {
            if (p.id().value().equals(pictureId)) {
                pictureToDelete = p;
                edition.pictures().remove(p);
                storageService.delete(pictureToDelete.url().value(), editionSlug.value());
                break;
            }
        }
        if (pictureToDelete == null) { throw new IllegalArgumentException("Picture NOT found!"); }
    }

    public Envelope<DeletePictureResponse> execute(String editionId, String pictureId) {
        EditionId id = EditionId.of(editionId);
        Edition edition = editionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Picture ID not found"));
        removePicture(pictureId, edition);
        Edition updated = editionRepository.update(edition);
        DeletePictureResponse data = new DeletePictureResponse(updated.editionId().value(), true);
        log.info("Picture deleted successfully.");
        return new Envelope<>(data, new Meta());
    }

}
