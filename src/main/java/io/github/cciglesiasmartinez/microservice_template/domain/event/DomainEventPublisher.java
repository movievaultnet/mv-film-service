package io.github.cciglesiasmartinez.microservice_template.domain.event;


public interface DomainEventPublisher {

    void publish(Object event);

}
