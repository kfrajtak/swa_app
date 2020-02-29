package cz.cvut.fel.still.app.integration;

import io.restassured.RestAssured;
import org.junit.Before;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.hasItems;

public class RestAssuredTests {
    @Before
    public void setup() {
        RestAssured.baseURI = "https://api.github.com";
    }

    //@Test
    public void whenRequestedGet_thenOK() {
        when().get("/api/demo/key1").then().body("$", hasItems(1, 2, 3));
    }
}
