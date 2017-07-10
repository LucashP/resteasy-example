package pl.lucash.resteasy.user;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pl.lucash.resteasy.user.dto.UserDTO;

public class JsonUserResourceIt {

    @Before
    public void setUp() throws Exception {
        System.out.println("setUp");
    }


    @Test
    public void getUsers() {
        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON.toString())
                .when()
                .get("/example/json/users");

        response.then().statusCode(200);
        response.getBody().prettyPrint();
    }

    @Test
    public void addUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName("firstName");
        userDTO.setLastName("lastName");

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        Response response = RestAssured
                .given()
                    .contentType(ContentType.JSON.toString())
                    .body(gson.toJson(userDTO))
                .when()
                    .post("/example/json/users");

        response.then().statusCode(200);
        response.getBody().prettyPrint();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("tearDown");
    }

}