package ar.com.laboratorio.steady.fabrica_revival.application;

import java.util.UUID;

import ar.com.laboratorio.steady.fabrica_revival.domain.LegacyClient;
import ar.com.laboratorio.steady.fabrica_revival.domain.contracts.ClientReactivator;
import ar.com.laboratorio.steady.fabrica_revival.domain.events.ClientEventType;
import ar.com.laboratorio.steady.fabrica_revival.domain.repositories.ClientEventRecorder;
import ar.com.laboratorio.steady.fabrica_revival.domain.repositories.ClientRepository;
import ar.com.laboratorio.steady.fabrica_revival.domain.vo.ClientDomainEvent;

public class ReactivateClientUseCase implements ClientReactivator {

    private final ClientRepository clientRepository;
    private final ClientEventRecorder clientEventRecorder;
    
    public ReactivateClientUseCase(ClientRepository clientRepository, ClientEventRecorder clientEventRecorder) {
        this.clientRepository = clientRepository;
        this.clientEventRecorder = clientEventRecorder;
    }

    @Override
    public void reactivate(UUID clientId) {
        LegacyClient client = clientRepository.findById(clientId);
        client.reactivate();
        clientRepository.save(client);
        
        var event = ClientDomainEvent.of(clientId, ClientEventType.REACTIVATED, "Application", String.format("Client reactivated successfully %s", clientId));
        clientEventRecorder.recordEntry(event);
    }

}
