package pl.lucash.resteasy.hello;

import io.restassured.response.Response;
import org.junit.Test;
import pl.lucash.resteasy.infrastructure.BaseIt;

import java.io.FileNotFoundException;

import static org.hamcrest.Matchers.equalTo;

public class HelloResourceIt extends BaseIt {

    @Test
    public void hello() throws FileNotFoundException {
        Response response = given()
                .when()
                .get("/hello/");
        then(response)
                .statusCode(200)
                .body("message", equalTo("Say Hello"));
    }
}