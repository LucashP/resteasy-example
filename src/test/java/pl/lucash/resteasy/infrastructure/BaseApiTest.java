package pl.lucash.resteasy.infrastructure;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;

public class BaseApiTest {

    protected static DatabaseConnector databaseConnector;

    @BeforeClass
    public static void beforeClass() throws Exception {
        String url = "jdbc:mysql://localhost:3306/resteasy";
        String user = "lpula";
        String password = "password";

        databaseConnector = DatabaseConnector.getInstance(url, user, password);
        databaseConnector.init();

        RestAssured.baseURI = RestAssured.DEFAULT_URI;
        RestAssured.port = RestAssured.DEFAULT_PORT;
        RestAssured.basePath = "/example";
    }

    protected RequestSpecification given() {
        return RestAssured.given()
                .log().all();
    }

    protected ValidatableResponse then(Response response) {
        return response.then()
                .log().all();
    }
}
