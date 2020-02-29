package cz.cvut.fel.still.app.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.Arrays;

//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Mono;

@Service
@Slf4j
public class JsonConsumerService {

    // NOTE this does **not** work, HTTP traffic is not intercepted
    /*public String getMessageWebClient() {
        // https://www.baeldung.com/spring-5-webclient
        String baseUrl = "http://localhost:3001";
        WebClient client = WebClient.create(baseUrl);
        log.info("Calling REST service (WebClient)");

        Mono<MessageResponse> response = client.get()
                .uri(baseUrl + "/?from=swa-app&type=webclient&ts=" + java.util.Date.from(Instant.now()).toString().replace(' ', '_'))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(MessageResponse.class);

        return response.block().getMessage();
    }*/

    public String getMessageRestTemplate() {
        String baseUrl = "http://localhost:3001";
        log.info("Calling REST service (RestTemplate)");

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        return restTemplate.exchange(
                baseUrl + "/?from=swa-app&type=webclient&ts=" + java.util.Date.from(Instant.now()).toString().replace(' ', '_'),
                HttpMethod.GET, entity, String.class).getBody();
    }

}
