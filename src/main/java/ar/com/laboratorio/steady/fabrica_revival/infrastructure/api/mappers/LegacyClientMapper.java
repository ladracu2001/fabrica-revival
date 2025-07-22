package ar.com.laboratorio.steady.fabrica_revival.infrastructure.api.mappers;

import ar.com.laboratorio.steady.fabrica_revival.domain.ClientHistory;
import ar.com.laboratorio.steady.fabrica_revival.domain.LegacyClient;
import ar.com.laboratorio.steady.fabrica_revival.domain.vo.FactoryCode;
import ar.com.laboratorio.steady.fabrica_revival.domain.vo.RevivalStatus;
import ar.com.laboratorio.steady.fabrica_revival.infrastructure.api.dto.LegacyClientRequest;
import ar.com.laboratorio.steady.fabrica_revival.infrastructure.persistence.models.LegacyClientEntity;

public interface LegacyClientMapper {

    default LegacyClient toEntity(LegacyClientRequest dto) {
        if (dto == null) {
            return null;
        }
        return new LegacyClient(FactoryCode.of(dto.factoryCode()));
    }

    default LegacyClient toDomainModelo(LegacyClientEntity entity) {
        if (entity == null) {
            return null;
        }
        var revivalStatus = new RevivalStatus(entity.getRevivalStatus());
        var history = new ClientHistory(entity.getId());
        return new LegacyClient(
            entity.getId(),
            FactoryCode.of(entity.getFactoryCode()),
            revivalStatus,
            entity.getCreatedAt(),
            history
        );
    }

    default LegacyClientEntity toEntityJpa(LegacyClient domain) {
        if (domain == null) {
            return null;
        }
        var entity = new LegacyClientEntity(domain.getId(), 
                                            domain.getFactoryCode().value(), 
                                            domain.getRevivalStatus().value(), 
                                            domain.getCreatedAt(), 
                                            new java.util.ArrayList<>());
        return entity;
    }
}
