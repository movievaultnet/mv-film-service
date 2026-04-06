package io.github.cciglesiasmartinez.microservice_template.infrastructure.event.edition;

import io.github.cciglesiasmartinez.microservice_template.domain.event.edition.EditionCreatedEvent;
import io.github.cciglesiasmartinez.microservice_template.domain.port.out.MessageBroker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Year;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EditionCreatedEventListenerTest {

    @Mock
    private MessageBroker messageBroker;

    @InjectMocks
    private EditionCreatedEventListener listener;

    @Test
    void shouldForwardEditionCreatedEventToMessageBroker() {
        EditionCreatedEvent event = EditionCreatedEvent.builder()
                .editionId("edition-1")
                .filmId("film-1")
                .slug("alien-1979")
                .filmTitle("Alien")
                .coverPicture("cover.jpg")
                .barCode("123456789")
                .country("Spain")
                .format("BluRay")
                .releaseYear(Year.of(2024))
                .packagingType("Steelbook")
                .notes("First pressing")
                .filmSummary("Sci-fi horror")
                .build();
        event.setRequestId("request-1");

        listener.handle(event);

        verify(messageBroker).sendMessage(event);
    }

}
