package io.github.cciglesiasmartinez.microservice_template.application.usecases;

import io.github.cciglesiasmartinez.microservice_template.application.port.in.EditionUseCase;
import io.github.cciglesiasmartinez.microservice_template.application.usecases.edition.*;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Envelope;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.ListGenericResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.edition.requests.CreateEditionRequest;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.edition.requests.UpdateEditionRequest;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.edition.responses.*;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.edition.wrappers.EditionWrapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
@Slf4j
public class EditionUseCaseImpl implements EditionUseCase {

    private final CreateEditionUseCase createEditionUseCase;
    private final GetEditionUseCase getEditionUseCase;
    private final UpdateEditionUseCase updateEditionUseCase;
    private final DeleteEditionUseCase deleteEditionUseCase;
    private final ListEditionsUseCase listEditionsUseCase;
    private final AddPictureUseCase addPictureUseCase;
    private final DeletePictureUseCase deletePictureUseCase;
    private final SetEditionCoverUseCase setEditionCoverUseCase;

    @Override
    public Envelope<CreateEditionResponse> createEdition(CreateEditionRequest request) {
        return createEditionUseCase.execute(request);
    }

    @Override
    public Envelope<CreatePictureResponse> addPicture(String editionId, MultipartFile file) {
        return addPictureUseCase.execute(editionId, file);
    }

    @Override
    public Envelope<GetEditionResponse> getEdition(String id) {
        return getEditionUseCase.execute(id);
    }

    @Override
    public Envelope<ListGenericResponse<EditionWrapper>> listAllEditions(int page, int size) {
        return listEditionsUseCase.execute(page, size);
    }

    @Override
    public Envelope<UpdateEditionResponse> updateEdition(UpdateEditionRequest request) {
        return updateEditionUseCase.execute(request);
    }

    @Override
    public Envelope<SetEditionCoverResponse> setEditionCover(String editionId, String pictureId) {
        return setEditionCoverUseCase.execute(editionId, pictureId);
    }

    @Override
    public Envelope<DeleteEditionResponse> deleteFilmById(String id) {
        return deleteEditionUseCase.execute(id);
    }

    @Override
    public Envelope<DeletePictureResponse> deletePicture(String editionId, String pictureId) {
        return deletePictureUseCase.execute(editionId, pictureId);
    }
}
