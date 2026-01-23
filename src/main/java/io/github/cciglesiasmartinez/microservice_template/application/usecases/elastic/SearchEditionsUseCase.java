package io.github.cciglesiasmartinez.microservice_template.application.usecases.elastic;

import io.github.cciglesiasmartinez.microservice_template.domain.shared.PageResult;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Envelope;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.ListGenericResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Meta;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.elastic.ElasticEditionWrapper;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.search.elasticsearch.edition.EditionDocument;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.search.elasticsearch.edition.EditionSearchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SearchEditionsUseCase {

    private final EditionSearchRepository editionSearchRepository;

    @Value("${app.api-base-path:/elastic}")
    private String basePath;

    private String buildLink(int page, int size) {
        String cleanBase = (basePath != null && basePath.endsWith("/"))
                ? basePath.substring(0, basePath.length() - 1)
                : basePath;
        return String.format("%s?page=%d&size=%d", cleanBase, page, size);
    }

    private List<ElasticEditionWrapper> getEditionWrappersList(PageResult<EditionDocument> pageResult) {
        return pageResult.content().stream()
                .map(e -> {
                    return ElasticEditionWrapper.builder()
                            .id(e.getId())
                            .filmId(e.getFilmId())
                            .slug(e.getSlug())
                            .filmTitle(e.getFilmTitle())
                            .coverPicture(e.getCoverPicture())
                            .barCode(e.getBarCode())
                            .country(e.getCountry())
                            .format(e.getFormat())
                            .releaseYear(e.getReleaseYear())
                            .packagingType(e.getPackagingType())
                            .notes(e.getNotes())
                            .build();
                }).toList();
    }

    public Envelope<ListGenericResponse<ElasticEditionWrapper>> execute(String query, int page, int size) {
        PageResult<EditionDocument> pageResult = editionSearchRepository.searchBySearchableText(query, page, size)
                .orElse(PageResult.empty());
        List<ElasticEditionWrapper> elements = getEditionWrappersList(pageResult);
        Integer nextPage = pageResult.hasNext() ? pageResult.page() + 1 : null;
        Integer prevPage = pageResult.hasPrevious() ? pageResult.page() - 1 : null;
        String currentLink = buildLink(pageResult.page(), pageResult.size());
        String nextLink    = pageResult.hasNext()     ? buildLink(pageResult.page() + 1, pageResult.size()) : null;
        String prevLink    = pageResult.hasPrevious() ? buildLink(Math.max(pageResult.page() - 1, 0), pageResult.size()) : null;
        ListGenericResponse<ElasticEditionWrapper> data = new ListGenericResponse<>(
                elements,
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
                prevLink
        );
        log.info("SearchEditionsUseCase executed successfully for query '{}'", query);
        return new Envelope<>(data, new Meta());
    }

}
