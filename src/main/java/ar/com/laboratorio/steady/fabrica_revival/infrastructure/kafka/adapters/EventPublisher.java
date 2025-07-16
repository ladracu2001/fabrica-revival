package ar.com.laboratorio.steady.fabrica_revival.infrastructure.kafka.adapters;

public interface EventPublisher {
    
    void publish(String topic, String key, Object payload);
}
