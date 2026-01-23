package io.github.cciglesiasmartinez.microservice_template.infrastructure.event.edition;

import io.github.cciglesiasmartinez.microservice_template.domain.event.edition.EditionCreatedEvent;
import io.github.cciglesiasmartinez.microservice_template.domain.event.edition.EditionUpdatedEvent;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.search.elasticsearch.edition.EditionDocument;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.search.elasticsearch.edition.EditionSearchRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
@Slf4j
public class EditionElasticsearchSyncHandler {

    private final EditionSearchRepository editionSearchRepository;
    private final ElasticsearchOperations elasticsearchOperations;

    @EventListener
    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handleEditionCreatedEvent(EditionCreatedEvent event) {
        log.info("Handling EditionCreatedEvent {} ", event.getRequestId());
        EditionDocument edition = EditionDocument.builder()
                .id(event.getEditionId())
                .filmId(event.getFilmId())
                .slug(event.getSlug())
                .filmTitle(event.getFilmTitle())
                .coverPicture(event.getCoverPicture())
                .barCode(event.getBarCode())
                .country(event.getCountry())
                .format(event.getFormat())
                .releaseYear(event.getReleaseYear().getValue())
                .packagingType(event.getPackagingType())
                .notes(event.getNotes())
                .indexedAt(LocalDateTime.now())
                .searchableText(buildSearchableText(
                        event.getFilmTitle(),
                        event.getBarCode(),
                        event.getCountry(),
                        event.getNotes()))
                .build();
        editionSearchRepository.save(edition);
    }

    @EventListener
    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handleEditionUpdatedEvent(EditionUpdatedEvent event) {
        log.info("Handling EditionUpdatedEvent {}", event.getRequestId());
        EditionDocument edition = getEditionDocument(event);
        updateEditionDocumentFromEvent(edition, event);
        editionSearchRepository.save(edition);
    }

    private EditionDocument getEditionDocument(EditionUpdatedEvent event) {
        return editionSearchRepository.findById(event.getEditionId())
                .orElseThrow(() -> {
                    String message = "Edition not found in Elasticsearch for id " + event.getEditionId();
                    return new RuntimeException(message);
                });
    }

    private String buildSearchableText(String filmTitle, String barCode, String country, String notes) {
        String[] texts = { filmTitle, barCode, country, notes };
        StringBuilder sb = new StringBuilder();
        for (String t : texts) {
            if (t != null && !t.isBlank()) {
                sb.append(t).append(" ").toString();
            }
        }
        return sb.toString().trim();
    }

    private void updateEditionDocumentFromEvent(EditionDocument edition, EditionUpdatedEvent event) {
        edition.setCoverPicture(event.getCoverPicture());
        edition.setBarCode(event.getBarCode());
        edition.setCountry(event.getCountry());
        edition.setFormat(event.getFormat());
        edition.setReleaseYear(event.getReleaseYear().getValue());
        edition.setPackagingType(event.getPackagingType());
        edition.setNotes(event.getNotes());
        edition.setSearchableText(buildSearchableText(
                event.getFilmTitle(),
                event.getBarCode(),
                event.getCountry(),
                event.getNotes())
        );
    }

}
