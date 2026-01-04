package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.messaging.kafka;

import io.github.cciglesiasmartinez.microservice_template.domain.event.DomainEvent;
import io.github.cciglesiasmartinez.microservice_template.domain.port.out.MessageBroker;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * Implementación concreta de {@link MessageBroker} que utiliza Apache Kafka como sistema de mensajería.
 * <p>
 * Esta clase actúa como productor de eventos de dominio ({@link DomainEvent}) hacia un tópico Kafka específico.
 * Se encarga de enviar los mensajes de manera asíncrona y registrar la operación utilizando el contexto de logging
 * proporcionado por {@link MDC}.
 * <p>
 * El propósito principal de este componente es desacoplar la lógica de dominio de la infraestructura de mensajería,
 * permitiendo así una arquitectura basada en eventos y preparada para escalar entre microservicios.
 */
@Slf4j
@Service
public class KafkaProducer implements MessageBroker {

    @Autowired
    private KafkaTemplate<String, DomainEvent> kafkaTemplate;

    /**
     * Envía un evento de dominio al tópico de Kafka configurado.
     * <p>
     * Este método utiliza el {@link KafkaTemplate} de Spring para enviar de forma asíncrona
     * un {@link DomainEvent} al tópico <code>item.events</code>, usando el <code>requestId</code> como clave de mensaje.
     * Si el envío es exitoso, se registra un mensaje informativo en el log.
     * En caso de error, se captura la excepción y se registra adecuadamente.
     * </p>
     *
     * @param event el evento de dominio que se desea publicar en Kafka.
     */
    @Override
    public void sendMessage(DomainEvent event) {
        String topicName = "item.events";
        CompletableFuture<SendResult<String, DomainEvent>> future = kafkaTemplate.send(topicName, event.getRequestId(), event);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                MDC.put("requestId", event.getRequestId());
                log.info("Sent event {} with offset {}.", event.getEventType(), result.getRecordMetadata().offset());
                MDC.clear();
            } else {
                MDC.put("requestId", event.getRequestId());
                log.info("Unable to send event {} due to {}.", event.getEventType(), ex.getMessage());
                MDC.clear();
                throw new RuntimeException();
            }
        });
    }
}