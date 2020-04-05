package cz.cvut.fel.still.app.integration;

import com.google.common.io.Resources;
import cz.cvut.fel.still.app.configuration.TestApiConfig;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.Header;
import org.mockserver.model.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.net.URL;
import java.nio.charset.StandardCharsets;

import static org.apache.http.HttpHeaders.ACCEPT;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;
import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.verify.VerificationTimes.exactly;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:test.properties")
@Import(TestApiConfig.class)
public class PetsControllerIntegrationTests {

    private ClientAndServer mockServer = startClientAndServer();

    @Autowired
    private ClientAndServer proxy;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @BeforeEach
    public void startMockServer() {
        mockServer.reset();
        proxy.reset();
    }

    @Test
    public void shouldLoadTheNamesOfPets() throws Exception {
        // body from https://petstore.swagger.io/#/pet/findPetsByStatus
        URL url = Resources.getResource("pets-response.json");
        String petsResponse = Resources.toString(url, StandardCharsets.UTF_8);

        // given
        HttpRequest httpRequest = request()
            .withMethod("GET")
            .withPath("/pets")
            .withHeader(ACCEPT, MediaType.APPLICATION_JSON_VALUE)
            .withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .withQueryStringParameter("limit", "10");

        proxy
            .when(httpRequest)
            .respond(
                response()
                    .withStatusCode(200)
                    .withHeaders(
                        new Header(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    )
                    .withBody(petsResponse)
            );

        // when
        MvcResult mvcResult = mockMvc.perform(get("/api/local-pets")).andReturn();
        proxy.verify(
            request()
                .withPath("/pets"),
            exactly(1)
        );
        String body = mvcResult.getResponse().getContentAsString();
        Assert.assertEquals("[\"ASYDog\",\"Brownie\",\"EndavaPetJmeter\",\"Goot Doggie\",\"Gosho\",\"ILYAAAAAAA\",\"IlyaBaekstrangeID\",\"Llama\",\"Panda\",\"Postman\",\"SRC_TIME_SIZE\",\"SamoCSKA\",\"Scrabby\",\"doggie\",\"xyz\"]", body);
    }


}
