package ar.com.laboratorio.steady.fabrica_revival.infrastructure.persistence.mappers;

import java.util.List;
import java.util.UUID;

import ar.com.laboratorio.steady.fabrica_revival.domain.vo.ClientHistoryEntry;
import ar.com.laboratorio.steady.fabrica_revival.infrastructure.persistence.models.ClientHistoryEntryEmbeddable;

public class ClientHistoryMapper {

    public static List<ClientHistoryEntryEmbeddable> toEmbeddable(List<ClientHistoryEntry> domainEntries) {
        return domainEntries.stream()
            .map(e -> new ClientHistoryEntryEmbeddable(
                e.id(), e.type(), e.details(), e.timestamp()
            )).toList();
    }
    public static ClientHistoryEntryEmbeddable toEmbeddable(ClientHistoryEntry e) {
        return new ClientHistoryEntryEmbeddable(e.id(), e.type(), e.details(), e.timestamp());
    }
    public static List<ClientHistoryEntry> toDomain(List<ClientHistoryEntryEmbeddable> embeddables, UUID clientId) {
        return embeddables.stream()
            .map(e -> new ClientHistoryEntry(
                e.getId(), clientId, e.getType(), e.getDetail(), e.getTimestamp()
            )).toList();
    }
}
