package io.github.cciglesiasmartinez.microservice_template.application.usecases.edition;

import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.Edition;
import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.Picture;
import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.valueobjects.EditionId;
import io.github.cciglesiasmartinez.microservice_template.domain.port.out.EditionRepository;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Envelope;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Meta;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.edition.responses.CreatePictureResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.storage.localstorage.StorageService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Add picture use case.
 */
@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class AddPictureUseCase {

    private EditionRepository editionRepository;
    private StorageService storageService;

    /**
     * Executes the add picture use case. It works by loading the edition, adding the picture to it,
     * then saving the edition and storing the file in the storage service.
     *
     * @param id    represents the edition id
     * @param file  is the picture {@link MultipartFile} to be added
     * @return      an {@link Envelope} containing a {@link CreatePictureResponse} with the result
     *
     * @throws RuntimeException if the edition id is not found
     * @throws RuntimeException if there is an error saving the file
     *
     */
    public Envelope<CreatePictureResponse> execute(String id, MultipartFile file) {
        EditionId editionId = EditionId.of(id);
        Edition edition = editionRepository.findById(editionId)
                .orElseThrow(() -> new RuntimeException("ID not found."));
        String fileNameExtension = storageService.getFileExtension(file);
        Picture picture = edition.addPicture(fileNameExtension);
        // TODO: Make this method void. Or use persist, but keep in mind about checking by id
        // and updating on the mapper (JPA's gotchas :)
        Edition updated = editionRepository.update(edition);
        storageService.save(edition.slug().value(), picture.id().value(), file);
        CreatePictureResponse data = new CreatePictureResponse(picture.id().value(), true);
        log.info("Picture added successfully.");
        return new Envelope<>(data, new Meta());
    }

}
