package io.github.cciglesiasmartinez.microservice_template.application.usecases;

import io.github.cciglesiasmartinez.microservice_template.application.port.in.ItemUseCase;
import io.github.cciglesiasmartinez.microservice_template.application.usecases.item.*;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Envelope;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.ListGenericResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.item.requests.CreateItemRequest;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.item.requests.UpdateItemRequest;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.item.responses.CreateItemResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.item.responses.DeleteItemResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.item.responses.GetItemResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.item.responses.UpdateItemResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.item.wrappers.CollectionItemWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ItemUseCaseImpl implements ItemUseCase {

    private CreateItemUseCase createItemUseCase;
    private UpdateItemUseCase updateItemUseCase;
    private DeleteItemUseCase deleteItemUseCase;
    private GetItemUseCase getItemUseCase;
    private GetUserCollectionUseCase getUserCollectionUseCase;

    @Override
    public Envelope<CreateItemResponse> createItem(CreateItemRequest request, String userId) {
        return createItemUseCase.execute(request, userId);
    }

    @Override
    public Envelope<GetItemResponse> getItem(String itemId, String userId) {
        return getItemUseCase.execute(itemId, userId);
    }

    @Override
    public Envelope<ListGenericResponse<CollectionItemWrapper>> getUserCollection(int page, int size, String userId) {
        return getUserCollectionUseCase.execute(page, size, userId);
    }

    @Override
    public Envelope<UpdateItemResponse> updateItem(UpdateItemRequest request, String userId) {
        return updateItemUseCase.execute(request, userId);
    }

    @Override
    public Envelope<DeleteItemResponse> deleteItem(String itemId, String userId) {
        return deleteItemUseCase.execute(itemId, userId);
    }

}
