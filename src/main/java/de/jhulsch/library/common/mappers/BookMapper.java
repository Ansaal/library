package de.jhulsch.library.common.mappers;

import de.jhulsch.library.persistence.entity.BookPdo;
import de.jhulsch.library.service.domain.Book;
import de.jhulsch.library.service.domain.ImmutableBook;
import org.springframework.stereotype.Component;

@Component
public class BookMapper extends BaseMapper<BookPdo, Book> {


    @Override
    public BookPdo toPdo(Book domainObject) {
        BookPdo bookPdo = new BookPdo();
        bookPdo.setBookId(domainObject.getBookId());
        bookPdo.setAuthor(domainObject.getAuthor());
        bookPdo.setGenre(domainObject.getGenre());
        bookPdo.setPublisher(domainObject.getPublisher());
        bookPdo.setTitle(domainObject.getTitle());
        return bookPdo;
    }

    @Override
    public Book toDomainObject(BookPdo pdo) {
       return ImmutableBook.builder()
                .bookId(pdo.getBookId())
                .title(pdo.getTitle())
                .author(pdo.getAuthor())
                .genre(pdo.getGenre())
                .publisher(pdo.getPublisher())
                .build();
    }
}
