package ar.com.laboratorio.steady.fabrica_revival.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import ar.com.laboratorio.steady.fabrica_revival.domain.events.ClientEventType;
import ar.com.laboratorio.steady.fabrica_revival.domain.vo.ClientHistoryEntry;

public class ClientHistory {

    private final UUID clientId;
    private final List<ClientHistoryEntry> events;

    public ClientHistory(UUID clientId) {
        this.clientId = clientId;
        this.events = new ArrayList<>();
    }

    public void recordEntry(ClientEventType type, String details) {
        events.add(ClientHistoryEntry.of(UUID.randomUUID(), clientId, type, details));
    }

    public List<ClientHistoryEntry> getEvents() {
        return Collections.unmodifiableList(events);
    }

    public void appendAuditEntry(String comment, LocalDateTime timestamp) {
        events.add(ClientHistoryEntry.of(
            UUID.randomUUID(),
            clientId,
            ClientEventType.AUDITED,
            comment
        ));
    }

    public void appendReactivationEntry(String actor) {
        events.add(ClientHistoryEntry.of(
            UUID.randomUUID(),
            clientId,
            ClientEventType.REACTIVATED,
            "Reactivado por: " + actor
        ));
    }

    public void appendCustom(ClientEventType type, String detail, LocalDateTime timestamp) {
        events.add(ClientHistoryEntry.of(
            UUID.randomUUID(),
            clientId,
            type,
            detail
        ));
    }

    public List<ClientHistoryEntry> filterBy(ClientEventType type) {
        return events.stream()
                     .filter(e -> e.type().equals(type))
                     .toList();
    }

    public boolean contains(ClientEventType type) {
        return events.stream().anyMatch(e -> e.type().equals(type));
    }

    public UUID getClientId() {
        return clientId;
    }

    public int count() {
        return events.size();
    }
}
