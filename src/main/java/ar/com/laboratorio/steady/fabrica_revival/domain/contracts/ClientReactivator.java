package ar.com.laboratorio.steady.fabrica_revival.domain.contracts;

import java.util.UUID;

public interface ClientReactivator {

    void reactivate(UUID clientId);
}
