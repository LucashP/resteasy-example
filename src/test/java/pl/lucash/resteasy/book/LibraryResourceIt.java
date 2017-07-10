package pl.lucash.resteasy.book;

import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class LibraryResourceIt {

    @Test
    public void getBooks() {
        given()
            .log().all()
        .when()
            .get("/example/library/books")
        .then()
            .log().all()
            .statusCode(200);
    }

    @Test
    public void getBookByIsbn() {
        String isbn = "GeneratedISBN";

        given()
            .log().all()
        .when()
            .get("/example/library/books/" + isbn)
        .then()
            .log().all()
            .statusCode(200);
    }

    @Test
    public void getBookById() {
        int id = 1;

        given()
            .log().all()
        .when()
            .get("/example/library/books/" + id)
        .then()
            .log().all()
            .statusCode(200);
    }

    @Test
    public void addBook() {
        String isbn = "GeneratedISBN";
        String name = "GeneratedName";

        given()
            .contentType(ContentType.JSON.toString())
            .queryParam("isbn", isbn)
            .queryParam("name", name)
            .log().all()
        .when()
            .post("/example/library/book/" + isbn)
        .then()
            .log().all()
            .statusCode(200);
    }
}