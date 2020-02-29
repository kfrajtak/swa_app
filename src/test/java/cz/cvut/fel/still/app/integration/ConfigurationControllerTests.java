package cz.cvut.fel.still.app.integration;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static io.restassured.RestAssured.get;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ConfigurationControllerTests {
    @LocalServerPort
    private int port; // bind the above RANDOM_PORT

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
    }

    @Test
    public void givenUrl_whenGetRequest_thenOK() {
        get("/whoami?name=X").then().assertThat().statusCode(200);
    }

    @Test
    public void givenUrl_whenGetRequestWithoutQueryParam_thenBadRequest() {
        get("/whoami").then().assertThat().statusCode(400);
    }
}
