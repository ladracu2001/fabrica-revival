package ar.com.laboratorio.steady.fabrica_revival.application;

import java.util.UUID;

import ar.com.laboratorio.steady.fabrica_revival.domain.contracts.ClientAuditor;

public class AuditClientUseCase implements ClientAuditor {

    @Override
    public void audit(UUID clientId, String observation) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'audit'");
    }

}
