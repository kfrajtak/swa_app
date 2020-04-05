package cz.cvut.fel.still.app.configuration;

import io.swagger.client.ApiClient;
import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.mockserver.integration.ClientAndServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@TestConfiguration
public class TestApiConfig {
    @Value("petClinicServiceUrl")
    String basePath;
    private ClientAndServer proxy;

    public TestApiConfig() {
        proxy = ClientAndServer.startClientAndServer();
        System.setProperty("http.proxyHost", "127.0.0.1");
        System.setProperty("http.proxyPort", String.valueOf(proxy.getLocalPort()));
    }

    @Bean
    @Profile("test")
    public ApiClient getApiClient() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHost httpHost = new HttpHost(
            System.getProperty("http.proxyHost"),
            Integer.parseInt(System.getProperty("http.proxyPort"))
        );
        DefaultProxyRoutePlanner defaultProxyRoutePlanner = new DefaultProxyRoutePlanner(httpHost);
        HttpClient httpClient = HttpClients.custom().setRoutePlanner(defaultProxyRoutePlanner).build();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(httpClient));

        ApiClient client = new ApiClient(restTemplate);
        client.setBasePath(httpHost.toURI());
        return client;
    }

    @Bean
    public ClientAndServer getProxy() {
        return proxy;
    }
}
