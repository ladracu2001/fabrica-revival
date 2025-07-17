package ar.com.laboratorio.steady.fabrica_revival.application;

import java.util.UUID;

import ar.com.laboratorio.steady.fabrica_revival.domain.LegacyClient;
import ar.com.laboratorio.steady.fabrica_revival.domain.contracts.ClientAuditor;
import ar.com.laboratorio.steady.fabrica_revival.domain.events.ClientEventType;
import ar.com.laboratorio.steady.fabrica_revival.domain.repositories.ClientEventRecorder;
import ar.com.laboratorio.steady.fabrica_revival.domain.repositories.ClientRepository;
import ar.com.laboratorio.steady.fabrica_revival.domain.vo.ClientDomainEvent;

public class AuditClientUseCase implements ClientAuditor {

    private final ClientRepository clientRepository;
    private final ClientEventRecorder clientEventRecorder;

    public AuditClientUseCase(ClientRepository clientRepository, ClientEventRecorder clientEventRecorder) {
        this.clientRepository = clientRepository;
        this.clientEventRecorder = clientEventRecorder;
    }

    @Override
    public void audit(UUID clientId, String observation) {
        LegacyClient client = this.clientRepository.findById(clientId);
        client.audit(observation);
        this.clientRepository.save(client);
        ClientDomainEvent event = ClientDomainEvent.of(client.getId(), ClientEventType.AUDITED, "Application", String.format("Cliente auditado %s", observation));
        this.clientEventRecorder.record(event);
    }

}
