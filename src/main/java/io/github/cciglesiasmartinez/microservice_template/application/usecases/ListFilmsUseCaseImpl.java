package io.github.cciglesiasmartinez.microservice_template.application.usecases;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.github.cciglesiasmartinez.microservice_template.domain.model.Film;
import io.github.cciglesiasmartinez.microservice_template.domain.port.out.FilmRepository;
import io.github.cciglesiasmartinez.microservice_template.domain.shared.PageResult;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.Envelope;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.Meta;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.listfilmsresponse.FilmInfo;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.listfilmsresponse.ListFilmsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ListFilmsUseCaseImpl implements ListFilmsUseCase {

    private final FilmRepository filmRepository;

    @Value("${app.api-base-path:/films}")
    private String basePath;

    @Override
    public Envelope<ListFilmsResponse> execute(int page, int size) {
        PageResult<Film> pr = filmRepository.findPage(page, size);

        // Map Domain -> DTO (resumen para listados)
        List<FilmInfo> items = pr.content().stream()
                .map(f -> new FilmInfo(
                        f.itemId().value(),
                        f.title().value(),
                        f.releaseYear().value(),
                        f.producingCountry().value(),
                        f.rating().value(),
                        f.poster() != null ? f.poster().value() : null
                ))
                .toList();

        Integer nextPage = pr.hasNext() ? pr.page() + 1 : null;
        Integer prevPage = pr.hasPrevious() ? pr.page() - 1 : null;

      
        String currentLink = link(pr.page(), pr.size());
        String nextLink    = pr.hasNext()     ? link(pr.page() + 1, pr.size()) : null;
        String prevLink    = pr.hasPrevious() ? link(Math.max(pr.page() - 1, 0), pr.size()) : null;

        ListFilmsResponse data = new ListFilmsResponse(
                items,
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

        log.info("Films page {} retrieved (size {}).", pr.page(), pr.size());
      
        return new Envelope<>(data, new Meta());
    }

    
    private String link(int page, int size) {
        String cleanBase = (basePath != null && basePath.endsWith("/"))
                ? basePath.substring(0, basePath.length() - 1)
                : basePath;
        return String.format("%s?page=%d&size=%d", cleanBase, page, size);
    }
}
