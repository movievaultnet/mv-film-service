package io.github.cciglesiasmartinez.microservice_template.infrastructure.event.item;

import io.github.cciglesiasmartinez.microservice_template.domain.port.out.MessageBroker;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Esta clase será el handler para el evento Item Created. En principio cada evento tendrá un handler
 * propio aunque a futuro será interesante considerar si podemos crear un handler global. Posiblemente esta última sea
 * la opción más idónea.
 */
@Slf4j
@Component
@AllArgsConstructor
public class ItemCreatedEventListener {

    private final MessageBroker messageBroker;

    /**
     * Este método realizará las acciones debidas cuando se emita un evento de tipo {@link ItemCreatedEvent}. En este
     * caso particular únicamente mandaremos un mensaje a un servidor Apache Kafka a través de la interfaz
     * {@link MessageBroker}.
     *
     * @param event Un evento de tipo {@link ItemCreatedEvent}.
     */
//    @Async
//    @EventListener
//    public void handle(ItemCreatedEvent event) {
//        MDC.put("requestId", event.getItemId().value());
//        log.info("Handling ItemCreatedEvent event for user {}.", event.getItemId().value());
//        messageBroker.sendMessage(event);
//        MDC.clear();
//    }

}
