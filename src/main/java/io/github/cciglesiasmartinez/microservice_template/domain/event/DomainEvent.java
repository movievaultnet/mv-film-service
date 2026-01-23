package io.github.cciglesiasmartinez.microservice_template.domain.event;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.slf4j.MDC;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@NoArgsConstructor
public abstract class DomainEvent {

    protected String requestId;
    protected String eventType;

    public DomainEvent(String eventType) {
        this.requestId = MDC.get("requestId");
        this.eventType = eventType;
    }

}
