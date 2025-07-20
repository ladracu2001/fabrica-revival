package ar.com.laboratorio.steady.fabrica_revival.infrastructure.kafka.publishers;

import org.springframework.kafka.core.KafkaTemplate;

import ar.com.laboratorio.steady.fabrica_revival.domain.repositories.ClientEventRecorder;
import ar.com.laboratorio.steady.fabrica_revival.domain.vo.ClientDomainEvent;

public class ClientEventPublisher implements ClientEventRecorder{

    private final KafkaTemplate<String, String> kafkaTemplate;

    public ClientEventPublisher(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void recordEntry(ClientDomainEvent event) {
     
        String topic = event.type().topicName();
        String payload = serializeEvent(event);
        
        kafkaTemplate.send(topic, event.clientId().toString(), payload)
            .thenAccept(result -> System.out.println("Message sent successfully: " + payload))
            .exceptionally(failure -> {
                System.err.println("Failed to send message: " + payload + ", due to: " + failure.getMessage());
                return null;
            });
    }

    private String serializeEvent(ClientDomainEvent event) {
        return "{ \"clientId\": \"" + event.clientId() + "\", " +
               "\"type\": \"" + event.type() + "\", " +
               "\"source\": \"" + event.source() + "\", " +
               "\"details\": \"" + event.details() + "\" }";
    }
}
