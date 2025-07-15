package ar.com.laboratorio.steady.fabrica_revival.domain;

import java.time.LocalDateTime;
import static java.util.Objects.isNull;
public record ClientHistoryEntry(LocalDateTime timestamp, String action, String details) {

    public ClientHistoryEntry {
        if (isNull(action) || action.isBlank()) 
            throw new IllegalArgumentException("La acción no puede estar vacía");
        if (isNull(details) || details.isBlank()) 
            throw new IllegalArgumentException("Los detalles no pueden estar vacios");
    }

    public static ClientHistoryEntry of(String action, String details) {
        return new ClientHistoryEntry(LocalDateTime.now(), action, details);
    }
}
