package ar.com.laboratorio.steady.fabrica_revival.domain.vo;

import java.time.Instant;
import java.util.UUID;

import ar.com.laboratorio.steady.fabrica_revival.domain.events.ClientEventType;

public record ClientDomainEvent(UUID clientId,
    ClientEventType type, 
    String source,        // Ej: "Kafka", "REST", "Manual"
    String details,       // Mensaje descriptivo
    Instant timestamp     // Momento del evento
) {
    public static ClientDomainEvent of(UUID clientId, ClientEventType type, String source, String details) {
        return new ClientDomainEvent(clientId, type, source, details, Instant.now());
    }
}
