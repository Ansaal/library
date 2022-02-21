package de.jhulsch.library.persistence.repository;

import de.jhulsch.library.service.domain.User;

import java.time.LocalDate;
import java.util.stream.Stream;

public interface UserRepository {

    /**
     * finds all {@link User}s, that are not deleted and have not currently borrowed anything.
     * @return Stream of {@link User}
     */
    Stream<User> findAllActiveUsersWithoutBook();

    /**
     * finds all {@link User}s that have borrowed at least one book on a given date.
     * @param date
     * @return
     */
    Stream<User> findAllBorrowersAtDate(LocalDate date);


}
