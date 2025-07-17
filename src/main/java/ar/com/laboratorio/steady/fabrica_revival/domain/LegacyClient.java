package ar.com.laboratorio.steady.fabrica_revival.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import ar.com.laboratorio.steady.fabrica_revival.domain.events.ClientEventType;
import ar.com.laboratorio.steady.fabrica_revival.domain.exceptions.LegacyClientException;
import ar.com.laboratorio.steady.fabrica_revival.domain.vo.FactoryCode;
import ar.com.laboratorio.steady.fabrica_revival.domain.vo.RevivalStatus;
import lombok.Getter;
import lombok.Setter;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;

@Getter
@Setter
public class LegacyClient {

    private final UUID id;
    private final FactoryCode factoryCode;
    private RevivalStatus revivalStatus;
    private final LocalDateTime createdAt;
    private final ClientHistory history;
    private boolean audited;
    private final List<String> auditObservations = new java.util.ArrayList<>();

    public LegacyClient(FactoryCode factoryCode) {
        this.id = UUID.randomUUID();
        this.factoryCode = requireNonNull(factoryCode);
        this.revivalStatus = RevivalStatus.dormant();
        this.createdAt = LocalDateTime.now();
        this.history = new ClientHistory(id);
        this.history.recordEntry(ClientEventType.IMPORTED, String.format("Cliente legado importado con código: %s", factoryCode));
    }

    private LegacyClient(UUID id, FactoryCode factoryCode, RevivalStatus revivalStatus,
                         LocalDateTime createdAt, ClientHistory history) {
        this.id = id;
        this.factoryCode = factoryCode;
        this.revivalStatus = revivalStatus;
        this.createdAt = createdAt;
        this.history = history;
    }

    public static LegacyClient create(FactoryCode factoryCode) {
        UUID id = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();
        ClientHistory history = new ClientHistory(id);

        history.appendCustom(ClientEventType.IMPORTED, "Cliente legado creado", now);

        return new LegacyClient(id, factoryCode, RevivalStatus.dormant(), now, history);
    }

    public void audit(String observation){
        if(isNull(observation) || observation.isBlank()) {
            throw new LegacyClientException("La observación no puede ser nula o vacía");
        }
        this.revivalStatus = RevivalStatus.audited();
        this.history.recordEntry(ClientEventType.AUDITED, String.format("Cliente auditado con observación: %s", observation));
    }

    public List<String> getAuditObservations() {
        return List.copyOf(this.auditObservations);
    }

    public void reactivate() {
        this.revivalStatus = this.revivalStatus.nextOnReactivation();
        history.recordEntry(ClientEventType.REACTIVATED, String.format("Estado cambiado a: %s", revivalStatus.value()));
    }
}
