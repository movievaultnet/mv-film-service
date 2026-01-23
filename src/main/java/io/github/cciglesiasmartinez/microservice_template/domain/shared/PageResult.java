package io.github.cciglesiasmartinez.microservice_template.domain.shared;

import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.search.elasticsearch.edition.EditionDocument;

import java.util.List;

public record PageResult <T>(
	List <T> content,
	int page,
	int size, 
	long totalElements,
	int totalPages,
	boolean hasNext,
	boolean hasPrevious
){
	// TODO: Might want to get rid of this later
    public static PageResult<EditionDocument> empty() {
		return new PageResult<>(
				List.of(),
				0,
				0,
				0L,
				0,
				false,
				false
		);
    }
}
