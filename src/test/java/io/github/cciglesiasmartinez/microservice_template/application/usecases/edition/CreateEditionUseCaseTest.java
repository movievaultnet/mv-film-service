package io.github.cciglesiasmartinez.microservice_template.application.usecases.edition;

import io.github.cciglesiasmartinez.microservice_template.domain.event.DomainEventPublisher;
import io.github.cciglesiasmartinez.microservice_template.domain.event.edition.EditionCreatedEvent;
import io.github.cciglesiasmartinez.microservice_template.domain.model.film.Film;
import io.github.cciglesiasmartinez.microservice_template.domain.model.film.valueobjects.Description;
import io.github.cciglesiasmartinez.microservice_template.domain.model.film.valueobjects.FilmId;
import io.github.cciglesiasmartinez.microservice_template.domain.model.film.valueobjects.Poster;
import io.github.cciglesiasmartinez.microservice_template.domain.model.film.valueobjects.ProducingCountry;
import io.github.cciglesiasmartinez.microservice_template.domain.model.film.valueobjects.Rating;
import io.github.cciglesiasmartinez.microservice_template.domain.model.film.valueobjects.ReleaseYear;
import io.github.cciglesiasmartinez.microservice_template.domain.model.film.valueobjects.Title;
import io.github.cciglesiasmartinez.microservice_template.domain.model.film.valueobjects.TmdbId;
import io.github.cciglesiasmartinez.microservice_template.domain.port.out.EditionRepository;
import io.github.cciglesiasmartinez.microservice_template.domain.port.out.FilmRepository;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.edition.requests.CreateEditionRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateEditionUseCaseTest {

    @Mock
    private EditionRepository editionRepository;

    @Mock
    private FilmRepository filmRepository;

    @Mock
    private DomainEventPublisher domainEventPublisher;

    @Captor
    private ArgumentCaptor<EditionCreatedEvent> eventCaptor;

    @InjectMocks
    private CreateEditionUseCase createEditionUseCase;

    @Test
    void shouldPublishEditionCreatedEventAfterPersistingEdition() {
        Film film = Film.of(
                FilmId.generate(),
                TmdbId.of(10L),
                Title.of("Alien"),
                Description.of("Sci-fi horror"),
                ReleaseYear.of(1979),
                ProducingCountry.of("USA"),
                Rating.of("8.5"),
                Poster.of("poster.jpg")
        );
        CreateEditionRequest request = new CreateEditionRequest(
                film.id().value(),
                "123456789",
                "Spain",
                "BluRay",
                2024,
                "Steelbook",
                "First pressing"
        );
        when(filmRepository.findById(film.id())).thenReturn(Optional.of(film));

        createEditionUseCase.execute(request);

        verify(editionRepository).persist(org.mockito.ArgumentMatchers.any());
        verify(domainEventPublisher).publish(eventCaptor.capture());
        EditionCreatedEvent event = eventCaptor.getValue();
        assertThat(event.getFilmId()).isEqualTo(film.id().value());
        assertThat(event.getFilmTitle()).isEqualTo("Alien");
        assertThat(event.getBarCode()).isEqualTo("123456789");
        assertThat(event.getFormat()).isEqualTo("BluRay");
        assertThat(event.getPackagingType()).isEqualTo("Steelbook");
        assertThat(event.getNotes()).isEqualTo("First pressing");
    }

}
