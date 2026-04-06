package io.github.cciglesiasmartinez.microservice_template.infrastructure.event.edition;

import io.github.cciglesiasmartinez.microservice_template.domain.event.edition.EditionCreatedEvent;
import io.github.cciglesiasmartinez.microservice_template.domain.port.out.MessageBroker;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class EditionCreatedEventListener {

    private final MessageBroker messageBroker;

    @Async
    @EventListener
    public void handle(EditionCreatedEvent event) {
        MDC.put("requestId", event.getRequestId());
        log.info("Handling EditionCreatedEvent for edition {}.", event.getEditionId());
        try {
            messageBroker.sendMessage(event);
        } finally {
            MDC.clear();
        }
    }

}
