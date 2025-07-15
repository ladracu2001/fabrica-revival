package ar.com.laboratorio.steady.fabrica_revival.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class ClientHistory {

    private final UUID clientId;
    private final List<ClientHistoryEntry> events;

    public ClientHistory(UUID clientId) {
        this.clientId = clientId;
        this.events = new ArrayList<>();
    }

    public void record(String action, String details) {
        events.add(ClientHistoryEntry.of(action, details));
    }

    public List<ClientHistoryEntry> getEvents() {
        return Collections.unmodifiableList(events);
    }

    public UUID getClientId() {
        return clientId;
    }
}
