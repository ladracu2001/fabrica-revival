package ar.com.laboratorio.steady.fabrica_revival.application;

import java.util.List;
import java.util.UUID;

import ar.com.laboratorio.steady.fabrica_revival.domain.contracts.ClientHistoryRetriever;
import ar.com.laboratorio.steady.fabrica_revival.domain.vo.ClientHistoryEntry;


public class RetrieveClientHistoryUseCase implements ClientHistoryRetriever {

    @Override
    public List<ClientHistoryEntry> getHistory(UUID clientId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getHistory'");
    }

}
