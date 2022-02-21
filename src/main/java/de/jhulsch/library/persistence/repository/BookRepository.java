package de.jhulsch.library.persistence.repository;

import de.jhulsch.library.service.domain.Book;

import java.time.LocalDate;
import java.util.UUID;
import java.util.stream.Stream;

public interface BookRepository {

    /**
     * Finds all {@link Book}s borrowed by a user between two dates.
     * @param userId User
     * @param from start of date range
     * @param book end of date range
     * @return Stream of {@link Book}.
     */
    Stream<Book> getBooksFromUserBetweenDates(UUID userId, LocalDate from, LocalDate book);

    /**
     * Finds all currently available {@link Book}s.
     * @return Stream of {@link Book}s
     */
    Stream<Book> getAvailableBooks();

}
