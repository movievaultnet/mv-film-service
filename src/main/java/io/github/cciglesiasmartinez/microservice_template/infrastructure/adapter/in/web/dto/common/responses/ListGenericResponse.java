package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.github.cciglesiasmartinez.microservice_template.domain.shared.PageResult;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.function.BiFunction;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ListGenericResponse<T> {

	private List<T> elements;
	private int page;
	private int size;
	private long totalElements;
	private int totalPages;
	private boolean hasNext;
	private boolean hasPrevious;
	private Integer nextPage;
	private Integer prevPage;
	private String currentLink;
	private String nextLink;
	private String prevLink;

	public static <T> ListGenericResponse<T> from(
			List<T> content,
			PageResult<?> pageResult,
			BiFunction<Integer, Integer, String> linkBuilder) {
		Integer nextPage = pageResult.hasNext() ? pageResult.page() + 1 : null;
		Integer prevPage = pageResult.hasPrevious() ? pageResult.page() - 1 : null;
		String currentLink = linkBuilder.apply(pageResult.page(), pageResult.size());
		String nextLink = pageResult.hasNext() ? linkBuilder.apply(pageResult.page() + 1, pageResult.size()) : null;
		String previousLink = pageResult.hasPrevious() ? linkBuilder.apply(pageResult.page() - 1, pageResult.size()) : null;
		return new ListGenericResponse<>(
				content,
				pageResult.page(),
				pageResult.size(),
				pageResult.totalElements(),
				pageResult.totalPages(),
				pageResult.hasNext(),
				pageResult.hasPrevious(),
				nextPage,
				prevPage,
				currentLink,
				nextLink,
				previousLink
		);
	}

}