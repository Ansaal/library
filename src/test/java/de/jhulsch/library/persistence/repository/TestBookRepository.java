package de.jhulsch.library.persistence.repository;

import de.jhulsch.library.LibraryApplication;
import de.jhulsch.library.service.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest(classes = {LibraryApplication.class})
class TestBookRepository {

    @Autowired
    private BookRepository bookRepository;


    @Test
    void testGetBooksFromUserBetweenDates() {


        LocalDate from = LocalDate.of(2020, 11, 27);
        LocalDate to = LocalDate.of(2020, 11, 30);

        Stream<Book> booksFromUserBetweenDates = this.bookRepository.getBooksFromUserBetweenDates(UUID.fromString("53a3e3b7-33ee-418b-a1fc-b3f1a98e1eed"), from, to);
        List<Book> books = booksFromUserBetweenDates.collect(Collectors.toList());
        assertNotEquals(0,books.size());


    }

    @Test
    @Transactional
    void testGetAvailableBooks()  {
        List<Book> books = this.bookRepository.getAvailableBooks()
                .collect(Collectors.toList());

        Set<Book> distinctBooks = new HashSet<>(books);
        assertEquals(distinctBooks.size(), books.size());
        assertNotEquals(0,books.size());


    }



}
