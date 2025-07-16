package ar.com.laboratorio.steady.fabrica_revival.domain.repositories;

import java.util.List;
import java.util.UUID;

import ar.com.laboratorio.steady.fabrica_revival.domain.LegacyClient;
import ar.com.laboratorio.steady.fabrica_revival.domain.vo.FactoryCode;

public interface ClientRepository {

    LegacyClient findById(UUID clientId);
    List<LegacyClient> findAll();
    void save(LegacyClient client);
    boolean existsByFactoryCode(FactoryCode factoryCode);
}
