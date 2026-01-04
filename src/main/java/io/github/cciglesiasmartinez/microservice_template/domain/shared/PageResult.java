package io.github.cciglesiasmartinez.microservice_template.domain.shared;

import java.util.List;

public record PageResult <T>(
	List <T> content,
	int page,
	int size, 
	long totalElements,
	int totalPages,
	boolean hasNext,
	boolean hasPrevious
){}
