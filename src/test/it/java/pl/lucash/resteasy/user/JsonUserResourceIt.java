package pl.lucash.resteasy.user;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import pl.lucash.resteasy.infrastructure.BaseIt;
import pl.lucash.resteasy.user.dto.UserDTO;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;

public class JsonUserResourceIt extends BaseIt {

    @Before
    public void before() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = Optional.ofNullable(classLoader.getResource("user.sql"))
                .map(url -> new File(url.getFile()))
                .orElseThrow(FileNotFoundException::new);
        databaseConnector.init();
        databaseConnector.runScript(file);
    }

    @Test
    public void getUsers() {
        Response response = given()
                .contentType(ContentType.JSON.toString())
                .when()
                .get("/json/users");

        then(response).statusCode(200);
    }

    @Test
    public void addUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName("firstName");
        userDTO.setLastName("lastName");

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        Response response = given()
                .contentType(ContentType.JSON.toString())
                .body(gson.toJson(userDTO))
                .when()
                .post("/json/users");

        then(response).statusCode(200);
    }

}