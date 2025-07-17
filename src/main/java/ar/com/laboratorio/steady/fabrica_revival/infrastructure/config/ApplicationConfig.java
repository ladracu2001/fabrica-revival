package ar.com.laboratorio.steady.fabrica_revival.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ar.com.laboratorio.steady.fabrica_revival.application.AuditClientUseCase;
import ar.com.laboratorio.steady.fabrica_revival.application.ImportLegacyClientUseCase;
import ar.com.laboratorio.steady.fabrica_revival.application.ReactivateClientUseCase;
import ar.com.laboratorio.steady.fabrica_revival.application.RetrieveClientHistoryUseCase;
import ar.com.laboratorio.steady.fabrica_revival.domain.repositories.ClientEventRecorder;
import ar.com.laboratorio.steady.fabrica_revival.domain.repositories.ClientHistoryRepository;
import ar.com.laboratorio.steady.fabrica_revival.domain.repositories.ClientRepository;

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
}
