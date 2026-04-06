package io.github.cciglesiasmartinez.microservice_template.application.usecases.elastic;

import io.github.cciglesiasmartinez.microservice_template.domain.shared.PageResult;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Envelope;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.ListGenericResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Meta;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.edition.responses.GetEditionResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.elastic.ElasticEditionWrapper;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.search.elasticsearch.edition.EditionDocument;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.search.elasticsearch.edition.EditionSearchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SearchEditionsUseCase {

    private final EditionSearchRepository editionSearchRepository;

    public Envelope<ListGenericResponse<ElasticEditionWrapper>> execute(String query, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<EditionDocument> searchResult = editionSearchRepository.findBySearchableText(query, pageRequest);

        List<ElasticEditionWrapper> editions = searchResult.getContent().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

        log.info("HEYHEYHEY Got the list {}", editions.toString());

        ListGenericResponse<ElasticEditionWrapper> response = new ListGenericResponse<>(
                editions,
                searchResult.getNumber(),
                searchResult.getSize(),
                searchResult.getTotalElements(),
                searchResult.getTotalPages(),
                searchResult.hasNext(),
                searchResult.hasPrevious(),
                searchResult.hasNext() ? searchResult.getNumber() + 1 : null,
                searchResult.hasPrevious() ? searchResult.getNumber() - 1 : null,
                null, // currentLink
                null, // nextLink
                null  // prevLink
        );

        return new Envelope<>(response, new Meta());
    }

    private ElasticEditionWrapper mapToResponse(EditionDocument doc) {
        // Create response manually as we don't have full Entity here
        log.info("THIS COMES FROM THE DOC {}", doc.toString());
        ElasticEditionWrapper response = new ElasticEditionWrapper();
        response.setId(doc.getId());
        response.setFilmId(doc.getFilmId());
        response.setFilmTitle(doc.getFilmTitle());
        response.setSlug(doc.getSlug());
        response.setBarCode(doc.getBarCode());
        response.setCountry(doc.getCountry());
        response.setFormat(doc.getFormat());
        response.setReleaseYear(doc.getReleaseYear() != null ? doc.getReleaseYear() : null);
        response.setPackagingType(doc.getPackagingType());
        response.setCoverPicture(doc.getCoverPicture());
        response.setNotes(doc.getNotes());
        response.setCoverPicture(doc.getCoverPicture());
        log.info("HEHEHEHEHEY GOT AN EDITION RESPONSE {}", response.toString());
        return response;
    }

}
