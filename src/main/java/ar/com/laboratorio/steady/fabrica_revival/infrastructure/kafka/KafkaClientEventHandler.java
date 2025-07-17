package ar.com.laboratorio.steady.fabrica_revival.infrastructure.kafka;

import ar.com.laboratorio.steady.fabrica_revival.infrastructure.kafka.messages.ClientKafkaMessage;

public interface KafkaClientEventHandler {

    void handle(ClientKafkaMessage message);
}
