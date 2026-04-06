package io.github.cciglesiasmartinez.microservice_template.domain.event.edition;

import io.github.cciglesiasmartinez.microservice_template.domain.event.DomainEvent;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Year;

@ToString
@Getter
@Setter
public class EditionUpdatedEvent extends DomainEvent {

    private final String editionId;
    private final String filmId;
    private final String slug;
    private final String filmTitle;
    private final String coverPicture;
    private final String barCode;
    private final String country;
    private final String format;
    private final Year releaseYear;
    private final String packagingType;
    private final String notes;
    private final String filmSummary;

    @Builder
    private EditionUpdatedEvent(
            String editionId,
            String filmId,
            String slug,
            String filmTitle,
            String coverPicture,
            String barCode,
            String country,
            String format,
            Year releaseYear,
            String packagingType,
            String notes,
            String filmSummary
    ) {
        super(EditionCreatedEvent.class.getSimpleName());
        this.editionId = editionId;
        this.filmId = filmId;
        this.slug = slug;
        this.filmTitle = filmTitle;
        this.coverPicture = coverPicture;
        this.barCode = barCode;
        this.country = country;
        this.format = format;
        this.releaseYear = releaseYear;
        this.packagingType = packagingType;
        this.notes = notes;
        this.filmSummary = filmSummary;
    }

}
