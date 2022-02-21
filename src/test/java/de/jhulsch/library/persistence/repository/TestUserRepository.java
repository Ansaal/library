package de.jhulsch.library.persistence.repository;

import de.jhulsch.library.LibraryApplication;
import de.jhulsch.library.service.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest(classes = {LibraryApplication.class})
class TestUserRepository {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    void testFindAllActiveUsersWithoutBook() {
        Set<User> collect = this.userRepository.findAllActiveUsersWithoutBook().collect(Collectors.toSet());
        assertNotEquals(0,collect.size());
    }

    @Test
    void testFindAllBorrowersAtDate() {
        LocalDate from = LocalDate.of(2020, 11, 27);
        Set<User> collect = this.userRepository.findAllBorrowersAtDate(from).collect(Collectors.toSet());
        assertNotEquals(0,collect.size());
    }



}
