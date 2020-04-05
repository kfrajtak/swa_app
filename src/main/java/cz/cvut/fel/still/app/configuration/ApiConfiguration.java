package cz.cvut.fel.still.app.configuration;

import io.swagger.client.ApiClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ApiConfiguration {
    @Value("petClinicServiceUrl")
    String basePath;

    @Bean()
    @Profile("dev")
    public ApiClient GetApiClient() {
        ApiClient client = new ApiClient();
        client.setBasePath(basePath);
        return client;
    }
}
