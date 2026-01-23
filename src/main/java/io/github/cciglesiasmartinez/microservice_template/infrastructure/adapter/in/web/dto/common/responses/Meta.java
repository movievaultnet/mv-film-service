package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.MDC;

import java.time.LocalDateTime;

/**
 * Representa la sección de metadatos estándar incluida en todas las respuestas de la API.
 * <p>
 * Forma parte del DTO de salida {@link Envelope}, el cual siempre incluirá dos secciones:
 * <ul>
 *     <li><b>data</b>: el cuerpo de la respuesta con la información solicitada.</li>
 *     <li><b>meta</b>: un objeto de tipo {@code Meta} con información contextual del request.</li>
 * </ul>
 * </p>
 *
 * <p>
 * La clase {@code Meta} proporciona datos de trazabilidad y diagnóstico útiles para el desarrollo y la observabilidad,
 * tales como:
 * </p>
 * <ul>
 *     <li>El identificador único de la petición ({@code requestId}) que permite correlacionar logs entre
 *     microservicios.</li>
 *     <li>La marca temporal ({@code timestamp}) en que se generó la respuesta.</li>
 * </ul>
 *
 * <p>
 * Además, cuando la respuesta corresponde a listados paginados, {@code Meta} incluye campos adicionales que describen
 * el estado de la paginación (página actual, tamaño, totales) y enlaces de navegación (self/next/prev) para facilitar
 * el consumo desde clientes.
 * </p>
 *
 * <p>
 * Este patrón de envoltorio ({@code Envelope + Meta}) promueve respuestas uniformes, facilita el seguimiento de
 * peticiones distribuidas y mejora la experiencia de depuración en entornos de arquitectura orientada a eventos (EOA).
 * </p>
 */
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Standard meta-data for all API responses.")
@Getter
@Setter
public class Meta {

    // ---------- Trazabilidad ----------

    @Schema(
            description = "Request UUID.",
            example = "7f5d429e-6a7e-4499-870c-8445cb71a1e3"
    )
    private String requestId;

    @Schema(
            description = "Local timestamp.",
            example = "2025-08-24T17:31:58.681726405"
    )
    private LocalDateTime timestamp;

    /**
     * Constructor por defecto.
     * <p>
     * Inicializa la trazabilidad con el {@code requestId} del MDC (si está presente)
     * y la marca temporal local en el momento de la creación.
     * </p>
     */
    public Meta() {
        this.requestId = MDC.get("requestId");
        this.timestamp = LocalDateTime.now();
    }
    
}

   

