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

@Service
@Slf4j
public class NodeJsService {
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
