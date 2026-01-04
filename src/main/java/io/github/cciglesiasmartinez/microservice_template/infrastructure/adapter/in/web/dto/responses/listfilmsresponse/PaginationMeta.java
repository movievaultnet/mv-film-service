package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.listfilmsresponse;

// Estos atributos me los metes en el DTO de ListFilmsResponse
public record PaginationMeta(
	
	int page,
    int size,
    long totalElements,
    int totalPages,
    boolean hasNext,
    boolean hasPrevious
) {}
