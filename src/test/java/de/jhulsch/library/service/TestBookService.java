package de.jhulsch.library.service;

import de.jhulsch.library.persistence.repository.BookRepository;
import de.jhulsch.library.service.domain.Book;
import de.jhulsch.library.service.domain.ImmutableBook;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TestBookService {

    private final BookService bookService;

    public TestBookService() {
        BookRepository mock = mock(BookRepository.class);
        this.bookService = new BookServiceImpl(mock);
        setupMocks(mock);
    }

    @Test
    void testGetBooksFromBetweenDates() {

        List<Book> books = this.bookService.getBooksFromBetweenDates(UUID.randomUUID(), LocalDate.now(),LocalDate.now())
                .collect(Collectors.toList());

        assertEquals(1,books.size());
    }


    @Test
    void testGetAvailableBooks()  {
        List<Book> books = this.bookService.getAvailableBooks()
                .collect(Collectors.toList());

        assertEquals(1,books.size());

    }



    private void setupMocks(BookRepository mock) {
        when(mock.getAvailableBooks()).thenReturn(Stream.of(
                ImmutableBook.builder()
                        .bookId(UUID.randomUUID())
                        .title("test")
                        .author("test")
                        .genre("test")
                        .publisher("test")
                        .build()
        ));

        when(mock.getBooksFromUserBetweenDates(any(),any(),any())).thenReturn(Stream.of(
                ImmutableBook.builder()
                        .bookId(UUID.randomUUID())
                        .title("test")
                        .author("test")
                        .genre("test")
                        .publisher("test")
                        .build()
        ));
    }
}
