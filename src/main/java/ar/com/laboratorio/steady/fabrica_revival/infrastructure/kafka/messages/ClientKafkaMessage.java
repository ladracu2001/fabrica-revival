package ar.com.laboratorio.steady.fabrica_revival.infrastructure.kafka.messages;

import java.time.Instant;
import java.util.Map;

import ar.com.laboratorio.steady.fabrica_revival.domain.events.ClientEventType;
import lombok.Getter;

@Getter
public class ClientKafkaMessage {

    private final String topic;
    private final String key;
    private final ClientEventType type;
    private final String payload; // JSON plano o serializado como String
    private final Instant timestamp;
    private final Map<String, String> headers;

    public ClientKafkaMessage(String topic, String key, ClientEventType type, String payload, Instant timestamp, Map<String, String> headers) {
        this.topic = topic;
        this.key = key;
        this.type = type;
        this.payload = payload;
        this.timestamp = timestamp;
        this.headers = headers;
    }


}
