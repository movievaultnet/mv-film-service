package io.github.cciglesiasmartinez.microservice_template.application.usecases.edition;

import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.Edition;
import io.github.cciglesiasmartinez.microservice_template.domain.port.out.EditionRepository;
import io.github.cciglesiasmartinez.microservice_template.domain.shared.PageResult;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Envelope;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.ListGenericResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Meta;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.edition.wrappers.EditionWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor // TODO: Solve this later. Maybe just @AllArgsConstructor?
@Slf4j
public class ListEditionsUseCase {

    @Value("${app.api-base-path:/edition}")
    private String basePath;

    private final EditionRepository editionRepository;

    private String buildLink(int page, int size) {
        String cleanBase = (basePath != null && basePath.endsWith("/"))
                ? basePath.substring(0, basePath.length() - 1)
                : basePath;
        return String.format("%s?page=%d&size=%d", cleanBase, page, size);
    }

    private List<EditionWrapper> getEditionListFrom(PageResult<Edition> pageResult) {
        return pageResult.content().stream()
                .map(EditionWrapper::from)
                .toList();
    }

    public Envelope<ListGenericResponse<EditionWrapper>> execute(int page, int size) {
        PageResult<Edition> pageResult = editionRepository.findPage(page, size);
        List<EditionWrapper> editions = getEditionListFrom(pageResult);
        ListGenericResponse<EditionWrapper> data = ListGenericResponse.from(editions, pageResult, this::buildLink);
        log.info("Editions page {} retrieved (size {}).", pageResult.page(), pageResult.size());
        return new Envelope<>(data, new Meta());
    }

}
