package ar.com.laboratorio.steady.fabrica_revival.application;

import java.util.UUID;

import ar.com.laboratorio.steady.fabrica_revival.domain.LegacyClient;
import ar.com.laboratorio.steady.fabrica_revival.domain.contracts.LegacyClientImporter;
import ar.com.laboratorio.steady.fabrica_revival.domain.events.ClientEventType;
import ar.com.laboratorio.steady.fabrica_revival.domain.repositories.ClientEventRecorder;
import ar.com.laboratorio.steady.fabrica_revival.domain.repositories.ClientRepository;
import ar.com.laboratorio.steady.fabrica_revival.domain.vo.ClientDomainEvent;
import ar.com.laboratorio.steady.fabrica_revival.domain.vo.FactoryCode;

public class ImportLegacyClientUseCase implements LegacyClientImporter {

    private final ClientRepository clientRepository;
    private final ClientEventRecorder clientEventRecorder;

    public ImportLegacyClientUseCase(ClientRepository clientRepository, ClientEventRecorder clientEventRecorder) {
        this.clientRepository = clientRepository;
        this.clientEventRecorder = clientEventRecorder;
    }

    @Override
    public UUID importLegacyClient(String factoryCode) {
    
        var _factoryCode = FactoryCode.of(factoryCode);
        if(clientRepository.existsByFactoryCode(_factoryCode)) {
            throw new IllegalArgumentException("Client with factory code " + factoryCode + " already exists.");
        }
        
        LegacyClient client = LegacyClient.create(_factoryCode);
        clientRepository.save(client);

        var events = ClientDomainEvent.of(client.getId(), ClientEventType.IMPORTED, "Application", String.format("Client imported from %s", factoryCode));
        clientEventRecorder.recordEntry(events);

        return client.getId();
    }
}
