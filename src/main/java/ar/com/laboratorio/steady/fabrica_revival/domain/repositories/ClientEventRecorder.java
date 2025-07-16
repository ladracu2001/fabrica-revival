package ar.com.laboratorio.steady.fabrica_revival.domain.repositories;

import ar.com.laboratorio.steady.fabrica_revival.domain.vo.ClientDomainEvent;

public interface ClientEventRecorder {

    void record(ClientDomainEvent event);
}
