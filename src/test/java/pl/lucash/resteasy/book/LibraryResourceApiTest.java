package pl.lucash.resteasy.book;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import pl.lucash.resteasy.infrastructure.BaseApiTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;

public class LibraryResourceApiTest extends BaseApiTest {

    @Before
    public void before() throws FileNotFoundException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = Optional.ofNullable(classLoader.getResource("api/sql/library.sql"))
                .map(url -> new File(url.getFile()))
                .orElseThrow(FileNotFoundException::new);

        databaseConnector.runScript(file);
    }

    @Test
    public void getBooks() {
        Response response = given()
                .when()
                .get("/library/books");
        then(response)
            .statusCode(200);
    }

    @Test
    public void getBookByIsbn() {
        String isbn = "GeneratedISBN1";

        Response response = given()
                .when()
                .get("/library/books/" + isbn);
        then(response)
            .statusCode(200);
    }

    @Test
    public void getBookById() {
        int id = 1;

        Response response = given()
                .when()
                .get("/library/books/" + id);
        then(response)
            .statusCode(200);
    }

    @Test
    public void addBook() {
        String isbn = "GeneratedISBN3";
        String name = "GeneratedName3";

        Response response = given()
                .contentType(ContentType.JSON.toString())
                .queryParam("isbn", isbn)
                .queryParam("name", name)
            .when()
                .post("/library/book/" + isbn);
        then(response)
            .statusCode(200);
    }
}