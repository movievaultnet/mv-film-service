package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web;

import io.github.cciglesiasmartinez.microservice_template.application.port.in.ItemUseCase;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Envelope;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.ListGenericResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.item.requests.CreateItemRequest;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.item.requests.UpdateItemRequest;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.item.responses.CreateItemResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.item.responses.DeleteItemResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.item.responses.GetItemResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.item.responses.UpdateItemResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.item.wrappers.CollectionItemWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/items")
@Tag(name = "Items", description = "Endpoints related to collection items.")
public class ItemController {

    private final ItemUseCase itemUseCase;

    @Operation(summary = "Creates a new item.", description = "Creates a new item with provided data.")
    @ApiResponses(@ApiResponse(responseCode = "201", description = "Item created successfully."))
    @PostMapping("")
    public ResponseEntity<Envelope<CreateItemResponse>> getItem(
            @Valid @RequestBody CreateItemRequest request,
            Authentication authentication) {
        Envelope<CreateItemResponse> response = itemUseCase.createItem(request, authentication.getName());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Updates an item.", description = "Updates a new item with provided data.")
    @ApiResponses(@ApiResponse(responseCode = "201", description = "Item updated successfully."))
    @PutMapping("")
    public ResponseEntity<Envelope<UpdateItemResponse>> updateItem(
            @Valid @RequestBody UpdateItemRequest request,
            Authentication authentication) {
        Envelope<UpdateItemResponse> response = itemUseCase.updateItem(request, authentication.getName());
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @Operation(summary = "Deletes an item.", description = "Deletes the item by its id.")
    @ApiResponses(@ApiResponse(responseCode = "201", description = "Item deleted successfully."))
    @DeleteMapping("{id}")
    public ResponseEntity<Envelope<DeleteItemResponse>> deleteItem(String id, Authentication authentication) {
        Envelope<DeleteItemResponse> response = itemUseCase.deleteItem(id, authentication.getName());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Retrieves an item.", description = "Retrieves an item by its id.")
    @ApiResponses(@ApiResponse(responseCode = "201", description = "Item retrieved successfully."))
    @GetMapping("{id}")
    public ResponseEntity<Envelope<GetItemResponse>> getItem(@PathVariable String id, Authentication authentication) {
        Envelope<GetItemResponse> response = itemUseCase.getItem(id, authentication.getName());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Retrieves the user collection.", description = "Retrieves the user collection.")
    @ApiResponses(@ApiResponse(responseCode = "201", description = "Collection retrieved successfully."))
    @GetMapping("/collection")
    public ResponseEntity<Envelope<ListGenericResponse<CollectionItemWrapper>>> getUserCollection(
            @RequestParam int page,
            @RequestParam int size,
            Authentication authentication) {
        Envelope<ListGenericResponse<CollectionItemWrapper>> response = itemUseCase.getUserCollection(
                page,
                size,
                authentication.getName());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}

