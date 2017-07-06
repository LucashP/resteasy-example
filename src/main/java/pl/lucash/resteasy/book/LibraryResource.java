package pl.lucash.resteasy.book;

import org.apache.log4j.Logger;
import pl.lucash.resteasy.book.dto.BookDTO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/library")
public class LibraryResource {

    private static final Logger LOGGER = Logger.getLogger(LibraryResource.class);
    private BookService bookService;

    public LibraryResource() {
    }

    @Inject
    public LibraryResource(BookService bookService) {
        this.bookService = bookService;
    }

    @GET
    @Path("/books")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<BookDTO> getBooks() {
        return bookService.all();
    }

    @GET
    @Path("/book/{isbn}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public BookDTO getBook(@PathParam("isbn") String isbn) {
        return bookService.find(isbn);
    }

    @POST
    @Path("/book/{isbn}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public BookDTO addBook(@PathParam("isbn") String id, @QueryParam("name") String name) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setIsbn(id);
        bookDTO.setName(name);
        return bookService.add(bookDTO);
    }

    @DELETE
    @Path("/book/{id}")
    public void removeBook(@PathParam("id") String id) {

    }

}
