package pl.lucash.resteasy.book;

import pl.lucash.resteasy.book.dto.BookDTO;

import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;

@Path("/library")
public class Library {

    @GET
    @Path("/books")
    public List<BookDTO> getBooks() {
        return new ArrayList<>();
    }

    @GET
    @Path("/book/{isbn}")
    public BookDTO getBook(@PathParam("isbn") String id) {
        // search my database and get a string representation and return it
        return new BookDTO();
    }

    @PUT
    @Path("/book/{isbn}")
    public void addBook(@PathParam("isbn") String id, @QueryParam("name") String name) {

    }

    @DELETE
    @Path("/book/{id}")
    public void removeBook(@PathParam("id") String id) {

    }

}
