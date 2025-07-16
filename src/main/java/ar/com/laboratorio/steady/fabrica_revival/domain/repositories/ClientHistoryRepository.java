package ar.com.laboratorio.steady.fabrica_revival.domain.repositories;

import java.util.List;
import java.util.UUID;

import ar.com.laboratorio.steady.fabrica_revival.domain.vo.ClientHistoryEntry;

public interface ClientHistoryRepository {

    void registerHistoryEntry(ClientHistoryEntry entry);
    List<ClientHistoryEntry> getHistory(UUID clientId);
}
