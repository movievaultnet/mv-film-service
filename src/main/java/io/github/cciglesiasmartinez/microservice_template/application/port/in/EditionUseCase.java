package io.github.cciglesiasmartinez.microservice_template.application.port.in;

import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Envelope;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.ListGenericResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.edition.requests.CreateEditionRequest;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.edition.requests.UpdateEditionRequest;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.edition.responses.*;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.edition.wrappers.EditionWrapper;
import org.springframework.web.multipart.MultipartFile;

public interface EditionUseCase {

    // Create
    Envelope<CreateEditionResponse> createEdition(CreateEditionRequest request);
    Envelope<CreatePictureResponse> addPicture(String editionId, MultipartFile file);

    // Read
    Envelope<GetEditionResponse> getEdition(String id);
    Envelope<ListGenericResponse<EditionWrapper>> listAllEditions(int page, int size);

    // Update
    Envelope<UpdateEditionResponse> updateEdition(UpdateEditionRequest request);
    Envelope<SetEditionCoverResponse> setEditionCover(String editionId, String pictureId);

    // Delete
    Envelope<DeleteEditionResponse> deleteFilmById(String id);
    Envelope<DeletePictureResponse> deletePicture(String editionId, String pictureId);

}
