package pl.lucash.resteasy.hello;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class HelloResourceIt {

    @Test
    public void hello() {
        Response response = RestAssured.get("/example/hello/");
        response.then().statusCode(200);
        response.then().body("message", equalTo("Say Hello"));
        response.getBody().prettyPrint();
    }
}