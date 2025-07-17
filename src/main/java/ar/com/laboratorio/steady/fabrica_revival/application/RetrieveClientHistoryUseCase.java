package ar.com.laboratorio.steady.fabrica_revival.application;

import java.util.List;
import java.util.UUID;

import ar.com.laboratorio.steady.fabrica_revival.domain.contracts.ClientHistoryRetriever;
import ar.com.laboratorio.steady.fabrica_revival.domain.repositories.ClientHistoryRepository;
import ar.com.laboratorio.steady.fabrica_revival.domain.vo.ClientHistoryEntry;


public class RetrieveClientHistoryUseCase implements ClientHistoryRetriever {

    private final ClientHistoryRepository clientHistoryRepository;

    public RetrieveClientHistoryUseCase(ClientHistoryRepository clientHistoryRepository) {
        this.clientHistoryRepository = clientHistoryRepository;
    }

    @Override
    public List<ClientHistoryEntry> getHistory(UUID clientId) {
        return clientHistoryRepository.getHistory(clientId);
    }
}
