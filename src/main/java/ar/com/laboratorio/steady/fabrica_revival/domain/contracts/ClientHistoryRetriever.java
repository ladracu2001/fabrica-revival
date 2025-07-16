package ar.com.laboratorio.steady.fabrica_revival.domain.contracts;

import java.util.List;
import java.util.UUID;

import ar.com.laboratorio.steady.fabrica_revival.domain.vo.ClientHistoryEntry;

public interface ClientHistoryRetriever {

    List<ClientHistoryEntry> getHistory(UUID clientId);
}
