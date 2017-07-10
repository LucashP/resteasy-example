package pl.lucash.resteasy;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class BaseIt {

    protected RequestSpecification given() {
        return RestAssured.given()
                .log().all();
    }

    protected ValidatableResponse then(Response response) {
        return response.then()
                .log().all();
    }
}
