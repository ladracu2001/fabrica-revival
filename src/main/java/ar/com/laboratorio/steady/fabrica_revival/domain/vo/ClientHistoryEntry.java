package ar.com.laboratorio.steady.fabrica_revival.domain.vo;

import java.time.LocalDateTime;
import java.util.UUID;

import ar.com.laboratorio.steady.fabrica_revival.domain.events.ClientEventType;
import ar.com.laboratorio.steady.fabrica_revival.domain.exceptions.ClientHistoryRecordException;

import static java.util.Objects.isNull;

public record ClientHistoryEntry(UUID id, UUID clientID, ClientEventType type, String details, LocalDateTime timestamp) {

    public ClientHistoryEntry {
        if (isNull(type)) 
            throw new ClientHistoryRecordException("La acción no puede estar vacía");
        if (isNull(details) || details.isBlank()) 
            throw new ClientHistoryRecordException("Los detalles no pueden estar vacios");
    }

    public static ClientHistoryEntry of(UUID id, UUID clientID, ClientEventType type, String details) {
        return new ClientHistoryEntry(id, clientID, type, details, LocalDateTime.now());
    }
}
