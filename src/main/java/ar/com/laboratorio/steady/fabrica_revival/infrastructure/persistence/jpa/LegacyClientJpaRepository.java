package ar.com.laboratorio.steady.fabrica_revival.infrastructure.persistence.jpa;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import ar.com.laboratorio.steady.fabrica_revival.infrastructure.persistence.models.LegacyClientEntity;

public interface LegacyClientJpaRepository extends JpaRepository<LegacyClientEntity, UUID> {
    @NonNull Optional<LegacyClientEntity> findById(@NonNull UUID clientId);
}
