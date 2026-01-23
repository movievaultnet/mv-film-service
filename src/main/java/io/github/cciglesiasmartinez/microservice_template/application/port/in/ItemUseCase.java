package io.github.cciglesiasmartinez.microservice_template.application.port.in;

import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Envelope;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.ListGenericResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.item.requests.CreateItemRequest;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.item.requests.UpdateItemRequest;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.item.responses.CreateItemResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.item.responses.DeleteItemResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.item.responses.GetItemResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.item.responses.UpdateItemResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.item.wrappers.CollectionItemWrapper;

public interface ItemUseCase {

    // Create
    Envelope<CreateItemResponse> createItem(CreateItemRequest request, String userId);

    // Read
    Envelope<GetItemResponse> getItem(String itemId, String userId);
    Envelope<ListGenericResponse<CollectionItemWrapper>> getUserCollection(int page, int size, String userId);

    // Update
    Envelope<UpdateItemResponse> updateItem(UpdateItemRequest request, String userId);

    // Delete
    Envelope<DeleteItemResponse> deleteItem(String itemId, String userId);

}
