package ar.com.laboratorio.steady.fabrica_revival.infrastructure.persistence.jpa;

import java.util.List;
import java.util.UUID;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import ar.com.laboratorio.steady.fabrica_revival.domain.LegacyClient;
import ar.com.laboratorio.steady.fabrica_revival.domain.repositories.ClientRepository;
import ar.com.laboratorio.steady.fabrica_revival.domain.vo.FactoryCode;
import ar.com.laboratorio.steady.fabrica_revival.infrastructure.api.mappers.LegacyClientMapper;
import ar.com.laboratorio.steady.fabrica_revival.infrastructure.persistence.models.LegacyClientEntity;

public class JpaClientRepository implements ClientRepository {

    private final EntityManager entityManager;

    public JpaClientRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public LegacyClient findById(UUID clientId) {
        LegacyClientEntity entity = entityManager.find(LegacyClientEntity.class, clientId);
        return entity != null ? new LegacyClientMapper(){}.toDomainModelo(entity) : null;
    }

    @Override
    public List<LegacyClient> findAll() {
        TypedQuery<LegacyClientEntity> query = entityManager.createQuery(
            "SELECT c FROM LegacyClientEntity c", LegacyClientEntity.class
        );
        List<LegacyClientEntity> entities = query.getResultList();
        List<LegacyClient> clients = new java.util.ArrayList<>();
        LegacyClientMapper mapper = new LegacyClientMapper() {};
        for (LegacyClientEntity entity : entities) {
            clients.add(mapper.toDomainModelo(entity));
        }
        return clients;
    }

    @Override
    public void save(LegacyClient client) {
        entityManager.merge(new LegacyClientMapper(){}.toEntityJpa(client));
    }

    @Override
    public boolean existsByFactoryCode(FactoryCode factoryCode) {
        TypedQuery<Long> query = entityManager.createQuery(
            "SELECT COUNT(c) FROM LegacyClientEntity c WHERE c.factoryCode = :value", Long.class
        );
        query.setParameter("value", factoryCode.value());
        return query.getSingleResult() > 0;
    }
}
