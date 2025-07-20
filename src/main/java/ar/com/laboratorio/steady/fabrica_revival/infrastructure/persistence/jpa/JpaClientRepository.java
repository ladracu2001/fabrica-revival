package ar.com.laboratorio.steady.fabrica_revival.infrastructure.persistence.jpa;

import java.util.List;
import java.util.UUID;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import ar.com.laboratorio.steady.fabrica_revival.domain.LegacyClient;
import ar.com.laboratorio.steady.fabrica_revival.domain.repositories.ClientRepository;
import ar.com.laboratorio.steady.fabrica_revival.domain.vo.FactoryCode;

public class JpaClientRepository implements ClientRepository {

    private final EntityManager entityManager;

    public JpaClientRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public LegacyClient findById(UUID clientId) {
        return entityManager.find(LegacyClient.class, clientId);
    }

    @Override
    public List<LegacyClient> findAll() {
        TypedQuery<LegacyClient> query = entityManager.createQuery(
            "SELECT c FROM LegacyClient c", LegacyClient.class
        );
        return query.getResultList();
    }

    @Override
    public void save(LegacyClient client) {
        entityManager.merge(client);
    }

    @Override
    public boolean existsByFactoryCode(FactoryCode factoryCode) {
        TypedQuery<Long> query = entityManager.createQuery(
            "SELECT COUNT(c) FROM LegacyClient c WHERE c.factoryCode.value = :value", Long.class
        );
        query.setParameter("value", factoryCode.value());
        return query.getSingleResult() > 0;
    }
}
