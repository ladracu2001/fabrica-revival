package ar.com.laboratorio.steady.fabrica_revival.infrastructure.persistence.jpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import static org.junit.jupiter.api.Assertions.*;

import ar.com.laboratorio.steady.fabrica_revival.domain.LegacyClient;
import ar.com.laboratorio.steady.fabrica_revival.domain.vo.FactoryCode;
import jakarta.persistence.EntityManager;

@Testcontainers
@DataJpaTest
public class JpaClientRepositoryTest {

    @SuppressWarnings({ "resource" })
    @Container
    static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.4")
        .withDatabaseName("testdb")
        .withUsername("test")
        .withPassword("test");
 
    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);
        registry.add("spring.datasource.driver-class-name", mysql::getDriverClassName);
    }
    @TestConfiguration
    static class JpaClientRepositoryTestConfig {
        @Bean
        public JpaClientRepository jpaClientRepository(EntityManager entityManager) {
            return new JpaClientRepository(entityManager);
        }
    }

    @Autowired
    private JpaClientRepository jpaClientRepository;

    @Test
    public void testSaveClient() {
        // Example test case to save a client
        LegacyClient client = new LegacyClient(FactoryCode.of("FCA-0123"));
        jpaClientRepository.save(client);
        
        LegacyClient foundClient = jpaClientRepository.findById(client.getId());
        assertNotNull(foundClient);
    }
    @Test
    public void testFindById() {
        LegacyClient client = LegacyClient.create(FactoryCode.of("FCA-0123"));
        jpaClientRepository.save(client);

        LegacyClient foundClient = jpaClientRepository.findById(client.getId());
        assertNotNull(foundClient);
    }
    @Test
    public void testExistsByFactoryCode() {
        FactoryCode factoryCode = FactoryCode.of("FCA-0123");

        boolean exists = jpaClientRepository.existsByFactoryCode(factoryCode);
        assertFalse(exists);
    }
}