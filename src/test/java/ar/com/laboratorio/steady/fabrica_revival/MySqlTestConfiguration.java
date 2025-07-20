package ar.com.laboratorio.steady.fabrica_revival;

import org.testcontainers.containers.MySQLContainer;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

//@TestConfiguration

public class MySqlTestConfiguration {
  //  @SuppressWarnings({ "resource" })
  //  static final MySQLContainer<?> mysql =
  //          new MySQLContainer<>("mysql:8.4")
  //                  .withDatabaseName("testdb")
  //                  .withUsername("test")
  //                  .withPassword("test");
//
  //  static {
  //      mysql.start();
  //  }
  //  @DynamicPropertySource
  //  static void configureProperties(DynamicPropertyRegistry registry) {
  //      registry.add("spring.datasource.url", mysql::getJdbcUrl);
  //      registry.add("spring.datasource.username", mysql::getUsername);
  //      registry.add("spring.datasource.password", mysql::getPassword);
  //      registry.add("spring.datasource.driver-class-name", mysql::getDriverClassName);
  //  }
}
