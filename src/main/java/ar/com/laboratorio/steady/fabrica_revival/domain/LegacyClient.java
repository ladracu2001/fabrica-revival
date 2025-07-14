package ar.com.laboratorio.steady.fabrica_revival.domain;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import static java.util.Objects.requireNonNull;

@Getter
@Setter
public class LegacyClient {

    private final UUID id;
    private final FactoryCode factoryCode;
    private RevivalStatus revivalStatus;
    private final LocalDateTime createdAt;

    public LegacyClient(FactoryCode factoryCode) {
        this.id = UUID.randomUUID();
        this.factoryCode = requireNonNull(factoryCode);
        this.revivalStatus = RevivalStatus.dormant();
        this.createdAt = LocalDateTime.now();
    }
    public void reactivate() {
        this.revivalStatus = this.revivalStatus.nextOnReactivation();
    }
}
