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

import java.time.LocalDate;
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
                .indexedAt(LocalDate.now())
                .filmSummary(event.getFilmSummary())
                .searchableText(buildSearchableText(
                        event.getFilmTitle(),
                        event.getBarCode(),
                        event.getCountry(),
                        event.getFilmSummary(),
                        event.getNotes()))
                .build();
        log.info("Saving EditionDocument {}", edition.toString());
        EditionDocument ed = editionSearchRepository.save(edition);
        log.info("Saved EditionDocument {}", ed.toString());
    }

    @EventListener
    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handleEditionUpdatedEvent(EditionUpdatedEvent event) {
        log.info("Handling EditionUpdatedEvent {}", event.toString());
        EditionDocument edition = getEditionDocument(event);
        updateEditionDocumentFromEvent(edition, event);
        log.info("!!!! UPDATING AND SAVING EditionDocument {}", edition.toString());
        editionSearchRepository.save(edition);
    }

    private EditionDocument getEditionDocument(EditionUpdatedEvent event) {
        return editionSearchRepository.findById(event.getEditionId())
                .orElseThrow(() -> {
                    String message = "Edition not found in Elasticsearch for id " + event.getEditionId();
                    return new RuntimeException(message);
                });
    }

    private String buildSearchableText(String filmTitle, String barCode, String country, String description, String notes) {
        String[] texts = { filmTitle, barCode, country, description, notes };
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
                event.getFilmSummary(),
                event.getNotes())
        );
    }

}
