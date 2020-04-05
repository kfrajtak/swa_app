package cz.cvut.fel.still.app;

import cz.cvut.fel.still.app.configuration.TestApiConfig;
import cz.cvut.fel.still.app.services.BuildInfo;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.junit.Assert.assertThat;

@SpringBootTest()
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:test.properties")
@Import(TestApiConfig.class)
public class BuildInfoServiceIntegrationTest {
    @Autowired
    private BuildInfo service;

    @Test
    void whenGetApplicationDescription_thenSuccess() {
        assertThat(service.getApplicationName(), Matchers.is("swa_demo_application"));
        assertThat(service.getApplicationDescription(), Matchers.is("Application for SWA course"));
        assertThat(service.getApplicationVersion(), Matchers.is("0.0.1-SNAPSHOT"));
    }
}
