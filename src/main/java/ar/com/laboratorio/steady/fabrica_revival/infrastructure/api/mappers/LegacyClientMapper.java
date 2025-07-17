package ar.com.laboratorio.steady.fabrica_revival.infrastructure.api.mappers;

import ar.com.laboratorio.steady.fabrica_revival.domain.LegacyClient;
import ar.com.laboratorio.steady.fabrica_revival.domain.vo.FactoryCode;
import ar.com.laboratorio.steady.fabrica_revival.infrastructure.api.dto.LegacyClientRequest;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LegacyClientMapper {

    LegacyClientMapper INSTANCE = Mappers.getMapper(LegacyClientMapper.class);
    
    @Mapping(target = "factoryCode", source = "factoryCode", qualifiedByName = "toFactoryCode")
    LegacyClient toEntity(LegacyClientRequest dto);

    @Named("toFactoryCode")
    default FactoryCode toFactoryCode(String code) {
        return FactoryCode.of(code); // encapsula validación en VO
    }

}
