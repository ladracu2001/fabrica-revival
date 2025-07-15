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
    private final ClientHistory history;

    public LegacyClient(FactoryCode factoryCode) {
        this.id = UUID.randomUUID();
        this.factoryCode = requireNonNull(factoryCode);
        this.revivalStatus = RevivalStatus.dormant();
        this.createdAt = LocalDateTime.now();
        this.history = new ClientHistory(id);
        this.history.record("IMPORT", String.format("Cliente legado importado con código: %s", factoryCode));
    }
    public void reactivate() {
        this.revivalStatus = this.revivalStatus.nextOnReactivation();
        history.record("REACTIVATION", String.format("Estado cambiado a: %s", revivalStatus.value()));
    }
}
