package pl.lucash.resteasy.book;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pl.lucash.resteasy.BaseIt;

public class LibraryResourceIt extends BaseIt {

    @Test
    public void getBooks() {
        Response response = given()
                .when()
                .get("/example/library/books");
        then(response)
            .statusCode(200);
    }

    @Test
    public void getBookByIsbn() {
        String isbn = "GeneratedISBN";

        Response response = given()
                .when()
                .get("/example/library/books/" + isbn);
        then(response)
            .statusCode(200);
    }

    @Test
    public void getBookById() {
        int id = 1;

        Response response = given()
                .when()
                .get("/example/library/books/" + id);
        then(response)
            .statusCode(200);
    }

    @Test
    public void addBook() {
        String isbn = "GeneratedISBN";
        String name = "GeneratedName";

        Response response = given()
                .contentType(ContentType.JSON.toString())
                .queryParam("isbn", isbn)
                .queryParam("name", name)
            .when()
                .post("/example/library/book/" + isbn);
        then(response)
            .statusCode(200);
    }
}