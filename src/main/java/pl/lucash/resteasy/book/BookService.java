package pl.lucash.resteasy.book;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import pl.lucash.resteasy.book.dto.BookDTO;
import pl.lucash.resteasy.infrastructure.AppConfig;
import pl.lucash.resteasy.infrastructure.DataSource;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
class BookService {

    private static final Logger LOGGER = Logger.getLogger(BookService.class);
    private DozerBeanMapper dozerBeanMapper;
    private DataSource dataSource;

    BookService() {
    }

    @Inject
    BookService(AppConfig appConfig) {
        this.dozerBeanMapper = appConfig.getDozerBeanMapper();
        this.dataSource = appConfig.getDataSource();
    }

    BookDTO add(BookDTO bookDTO) {
        Book book = dozerBeanMapper.map(bookDTO, Book.class);
        Session session = dataSource.beginTransaction();
        Serializable serializable = session.save(book);
        book = (Book) session.get(Book.class, serializable);
        dataSource.endTransaction(session);
        return dozerBeanMapper.map(book, BookDTO.class);
    }

    @SuppressWarnings("unchecked")
    List<BookDTO> all() {
        Session session = dataSource.beginTransaction();
        List<Book> result = (List<Book>) session.createQuery("from Book").list();
        dataSource.endTransaction(session);
        return result.stream().map(book -> dozerBeanMapper.map(book, BookDTO.class)).collect(Collectors.toList());
    }

    BookDTO find(String isbn) {
        Session session = dataSource.beginTransaction();
        Book book = (Book) session.createCriteria(Book.class)
                .add(Restrictions.eq("isbn", isbn))
                .uniqueResult();
        Optional.ofNullable(book).ifPresent(b -> LOGGER.info(b.toString()));
        dataSource.endTransaction(session);

        return Optional.ofNullable(book)
                .map(b -> dozerBeanMapper.map(b, BookDTO.class))
                .orElseThrow(() -> new NotFoundException("Book with isbn " + isbn));
    }

    BookDTO find(Integer id) {
        Session session = dataSource.beginTransaction();
        Book book = (Book) session.get(Book.class, id);
        Optional.ofNullable(book).ifPresent(b -> LOGGER.info(b.toString()));
        dataSource.endTransaction(session);

        return Optional.ofNullable(book)
                .map(b -> dozerBeanMapper.map(b, BookDTO.class))
                .orElseThrow(() -> new NotFoundException("Book with id " + id));
    }
}
