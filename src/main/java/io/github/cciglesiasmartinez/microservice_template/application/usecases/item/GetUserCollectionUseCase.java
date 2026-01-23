package io.github.cciglesiasmartinez.microservice_template.application.usecases.item;

import io.github.cciglesiasmartinez.microservice_template.domain.port.out.ItemRepository;
import io.github.cciglesiasmartinez.microservice_template.domain.shared.PageResult;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Envelope;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.ListGenericResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Meta;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.item.wrappers.CollectionItemWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class GetUserCollectionUseCase {

    @Value("${app.api-base-path:/items}")
    private String basePath;

    private final ItemRepository itemRepository;

    private String link(int page, int size) {
        String cleanBase = (basePath != null && basePath.endsWith("/"))
                ? basePath.substring(0, basePath.length() - 1)
                : basePath;
        return String.format("%s?page=%d&size=%d", cleanBase, page, size);
    }

    public Envelope<ListGenericResponse<CollectionItemWrapper>> execute(int page, int size, String userId) {
        PageResult<CollectionItemWrapper> pr = itemRepository.findPageByUserId(page, size, userId);
        Integer nextPage = pr.hasNext() ? pr.page() + 1 : null;
        Integer prevPage = pr.hasPrevious() ? pr.page() - 1 : null;


        String currentLink = link(pr.page(), pr.size());
        String nextLink    = pr.hasNext()     ? link(pr.page() + 1, pr.size()) : null;
        String prevLink    = pr.hasPrevious() ? link(Math.max(pr.page() - 1, 0), pr.size()) : null;

        ListGenericResponse<CollectionItemWrapper> data = new ListGenericResponse<>(
                pr.content(),
                pr.page(),
                pr.size(),
                pr.totalElements(),
                pr.totalPages(),
                pr.hasNext(),
                pr.hasPrevious(),
                nextPage,
                prevPage,
                currentLink,
                nextLink,
                prevLink
        );
        log.info("Retrieving item collection for user {}", userId);
        return new Envelope<>(data, new Meta());
    }

}
