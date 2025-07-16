package ar.com.laboratorio.steady.fabrica_revival.domain.contracts;

import java.util.UUID;

public interface ClientAuditor {

    void audit(UUID clientId, String observation);
}
