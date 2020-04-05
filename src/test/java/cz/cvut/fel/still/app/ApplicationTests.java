package cz.cvut.fel.still.app;

import cz.cvut.fel.still.app.configuration.TestApiConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:test.properties")
@Import(TestApiConfig.class)
class ApplicationTests {

	@Test
	void contextLoads() {
	}

}
