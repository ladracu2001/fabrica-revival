package ar.com.laboratorio.steady.fabrica_revival.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class LegacyClient {

    private final UUID id;
    private final FactoryCode factoryCode;
    private RevivalStatus revivalStatus;
    private final LocalDateTime createdAt;

}
