package pl.lucash.resteasy.book.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BookDTO {

    private String isbn;
    private String name;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
