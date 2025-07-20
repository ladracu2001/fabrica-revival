package ar.com.laboratorio.steady.fabrica_revival;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(MySqlTestConfiguration.class)
class FabricaRevivalApplicationTests {

	@Test
	void contextLoads() {
	}

}
