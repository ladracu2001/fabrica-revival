package ar.com.laboratorio.steady.fabrica_revival.infrastructure.persistence.jpa;

import java.util.List;
import java.util.UUID;

import ar.com.laboratorio.steady.fabrica_revival.domain.repositories.ClientHistoryRepository;
import ar.com.laboratorio.steady.fabrica_revival.domain.vo.ClientHistoryEntry;
import ar.com.laboratorio.steady.fabrica_revival.infrastructure.persistence.mappers.ClientHistoryMapper;
import ar.com.laboratorio.steady.fabrica_revival.infrastructure.persistence.models.LegacyClientEntity;

public class ClientHistoryJpaRepository implements ClientHistoryRepository {

    private final LegacyClientJpaRepository legacyClientJpaRepository;

    public ClientHistoryJpaRepository(LegacyClientJpaRepository legacyClientJpaRepository) {
        this.legacyClientJpaRepository = legacyClientJpaRepository;
    }

    @Override
    public void registerHistoryEntry(ClientHistoryEntry entry) {
        LegacyClientEntity legacyClient = legacyClientJpaRepository.findById(entry.clientID())
                .orElseThrow(() -> new IllegalArgumentException("Client not found: " + entry.clientID()));
        legacyClient.getHistoryEntries().add(ClientHistoryMapper.toEmbeddable(entry));
        legacyClientJpaRepository.save(legacyClient);
    }

    @Override
    public List<ClientHistoryEntry> getHistory(UUID clientId) {
       LegacyClientEntity legacyClient = legacyClientJpaRepository.findById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("Client not found: " + clientId));
       return ClientHistoryMapper.toDomain(legacyClient.getHistoryEntries(), clientId); 
    }
}
