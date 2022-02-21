package de.jhulsch.library.service;

import de.jhulsch.library.persistence.repository.BookRepository;
import de.jhulsch.library.service.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;
import java.util.stream.Stream;

@Component
public class BookServiceImpl implements BookService{

    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Stream<Book> getBooksFromBetweenDates(UUID userId, LocalDate from, LocalDate book) {
        return this.bookRepository.getBooksFromUserBetweenDates(userId,from,book);
    }

    @Override
    public Stream<Book> getAvailableBooks() {
        return this.bookRepository.getAvailableBooks();
    }
}
