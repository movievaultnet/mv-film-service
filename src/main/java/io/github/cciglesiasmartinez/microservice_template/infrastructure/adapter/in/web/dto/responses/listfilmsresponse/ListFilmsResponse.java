package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.listfilmsresponse;

public record ListFilmsResponse(
	    java.util.List<FilmInfo> films,
	    int page,
	    int size,
	    long totalElements,
	    int totalPages,
	    boolean hasNext,
	    boolean hasPrevious,
	    Integer nextPage,
	    Integer prevPage,
	    String currentLink,
	    String nextLink,
	    String prevLink
	) {}