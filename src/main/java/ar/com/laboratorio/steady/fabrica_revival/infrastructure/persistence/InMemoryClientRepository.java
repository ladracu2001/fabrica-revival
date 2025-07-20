package ar.com.laboratorio.steady.fabrica_revival.infrastructure.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import ar.com.laboratorio.steady.fabrica_revival.domain.LegacyClient;
import ar.com.laboratorio.steady.fabrica_revival.domain.repositories.ClientRepository;
import ar.com.laboratorio.steady.fabrica_revival.domain.vo.FactoryCode;

public class InMemoryClientRepository implements ClientRepository {

    private final Map<UUID, LegacyClient> store = new HashMap<>();

    @Override
    public LegacyClient findById(UUID clientId) {
        return store.get(clientId);
    }

    @Override
    public List<LegacyClient> findAll() {
        return List.copyOf(store.values());
    }

    @Override
    public void save(LegacyClient client) {
        store.put(client.getId(), client);
    }

    @Override
    public boolean existsByFactoryCode(FactoryCode factoryCode) {
        return store.values().stream()
            .anyMatch(c -> c.getFactoryCode().equals(factoryCode));
    }
}