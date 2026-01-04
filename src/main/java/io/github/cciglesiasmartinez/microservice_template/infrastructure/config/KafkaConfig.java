package io.github.cciglesiasmartinez.microservice_template.infrastructure.config;

import io.github.cciglesiasmartinez.microservice_template.domain.event.DomainEvent;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
@Configuration
public class KafkaConfig {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    /**
     * Crea un cliente administrativo de Kafka.
     * <p>
     * Este bean permite a Spring Boot interactuar con el clúster de Kafka para tareas administrativas, como la
     * creación automática de tópicos definidos en la aplicación.
     * </p>
     * @return Un objeto {@link KafkaAdmin} configurado con la dirección del broker.
     */
    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    /**
     * Crea el tópico principal del servicio si no existe.
     * <p>
     * En este caso, se define el tópico <strong>"item.events"</strong>, que será utilizado por el productor de eventos
     * del microservicio. Si el tópico ya existe en el clúster, esta configuración no generará conflicto y no intentará
     * recrearlo.
     * </p>
     * @return Un objeto {@link NewTopic} representando el tópico "item.events".
     */
    @Bean
    public NewTopic topic1() {
        return new NewTopic("item.events", 1, (short) 1);
    }

    /**
     * Configura la fábrica de productores Kafka.
     * <p>
     * Define la configuración necesaria para crear productores de mensajes, incluyendo:
     * </p>
     * <ul>
     *     <li>El servidor bootstrap de Kafka (dirección del broker).</li>
     *     <li>El serializador de claves ({@link StringSerializer}).</li>
     *     <li>El serializador de valores ({@link JsonSerializer}), que convierte
     *     los objetos de tipo {@link DomainEvent} en JSON antes de enviarlos.</li>
     * </ul>
     *
     * @return Un {@link ProducerFactory} parametrizado con clave {@code String} y valor {@code DomainEvent}.
     */
    @Bean
    public ProducerFactory<String, DomainEvent> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapAddress);
        configProps.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        configProps.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    /**
     * Crea una plantilla de Kafka para enviar mensajes.
     * <p>
     * El {@link KafkaTemplate} facilita la producción de eventos a Kafka de forma
     * sencilla y reutilizable dentro de la aplicación. Este bean será inyectado en el
     * adaptador de salida que se encarga de publicar los eventos ({@code KafkaProducer}).
     * </p>
     *
     * @return Un {@link KafkaTemplate} configurado para enviar objetos {@link DomainEvent}.
     */
    @Bean
    public KafkaTemplate<String, DomainEvent> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

}
