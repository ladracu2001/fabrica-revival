package ar.com.laboratorio.steady.fabrica_revival.infrastructure.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;

import ar.com.laboratorio.steady.fabrica_revival.application.AuditClientUseCase;
import ar.com.laboratorio.steady.fabrica_revival.application.ImportLegacyClientUseCase;
import ar.com.laboratorio.steady.fabrica_revival.application.ReactivateClientUseCase;
import ar.com.laboratorio.steady.fabrica_revival.application.RetrieveClientHistoryUseCase;
import ar.com.laboratorio.steady.fabrica_revival.domain.repositories.ClientEventRecorder;
import ar.com.laboratorio.steady.fabrica_revival.domain.repositories.ClientHistoryRepository;
import ar.com.laboratorio.steady.fabrica_revival.domain.repositories.ClientRepository;
import ar.com.laboratorio.steady.fabrica_revival.infrastructure.kafka.publishers.ClientEventPublisher;
import ar.com.laboratorio.steady.fabrica_revival.infrastructure.persistence.jpa.ClientHistoryJpaRepository;
import ar.com.laboratorio.steady.fabrica_revival.infrastructure.persistence.jpa.JpaClientRepository;
import ar.com.laboratorio.steady.fabrica_revival.infrastructure.persistence.jpa.LegacyClientJpaRepository;
import jakarta.persistence.EntityManager;

@Configuration
public class ApplicationConfig {

    @Bean
    public ImportLegacyClientUseCase importLegacyClientUseCase(ClientRepository clientRepository, ClientEventRecorder clientEventRecorder) {
        return new ImportLegacyClientUseCase(clientRepository, clientEventRecorder);
    }

    @Bean
    public AuditClientUseCase auditClientUseCase(ClientRepository clientRepository, ClientEventRecorder clientEventRecorder) {
        return new AuditClientUseCase(clientRepository, clientEventRecorder);
    }

    @Bean
    public ReactivateClientUseCase reactivateClientUseCase(ClientRepository clientRepository, ClientEventRecorder clientEventRecorder) {
        return new ReactivateClientUseCase(clientRepository, clientEventRecorder);
    }
    
    @Bean
    public RetrieveClientHistoryUseCase retrieveClientHistoryUseCase(ClientHistoryRepository clientHistoryRepository) {
        return new RetrieveClientHistoryUseCase(clientHistoryRepository);
    }

    @Bean
    public ClientRepository clientRepository(EntityManager entityManager) {
        return new JpaClientRepository(entityManager);
    }

    @Bean
    public ClientEventRecorder clientEventRecorder(KafkaTemplate<String, String> kafkaTemplate) {
        return new ClientEventPublisher(kafkaTemplate); 
    }
    
    @Bean
    public ClientHistoryRepository clientHistoryRepository(LegacyClientJpaRepository legacyClientJpaRepository) {
        return new ClientHistoryJpaRepository(legacyClientJpaRepository);
    }
    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        return new KafkaAdmin(configs);
    }
    @Bean
    public NewTopic clientLegacyImportedTopic() {
        return new NewTopic("client.legacy.imported", 1, (short) 1);
    }
}
