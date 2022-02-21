package de.jhulsch.library.service;

import de.jhulsch.library.common.Gender;
import de.jhulsch.library.persistence.repository.BookRepository;
import de.jhulsch.library.persistence.repository.UserRepository;
import de.jhulsch.library.service.domain.ImmutableUser;
import de.jhulsch.library.service.domain.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TestUserService {


    private final UserService userService;

    public TestUserService() {
        UserRepository mock = mock(UserRepository.class);
        this.userService = new UserServiceImpl(mock);
        setupMocks(mock);
    }



    @Test
    void testFindAllActiveUsersWithoutBook() {
        Set<User> collect = this.userService.findAllActiveUsersWithoutBook().collect(Collectors.toSet());
        assertNotEquals(0,collect.size());
    }

    @Test
    void testFindAllBorrowersAtDate() {
        LocalDate from = LocalDate.of(2020, 11, 27);
        Set<User> collect = this.userService.findAllBorrowersAtDate(from).collect(Collectors.toSet());
        assertNotEquals(0,collect.size());
    }

    private void setupMocks(UserRepository mock) {


        when(mock.findAllBorrowersAtDate(any())).thenReturn(
                Stream.of(
                        ImmutableUser.builder()
                                .userId(UUID.randomUUID())
                                .name("test")
                                .firstname("test")
                                .gender(Gender.d)
                                .createdOn(LocalDate.EPOCH)
                                .build()
                )
        );
        when(mock.findAllActiveUsersWithoutBook()).thenReturn(
                Stream.of(
                        ImmutableUser.builder()
                                .userId(UUID.randomUUID())
                                .name("test")
                                .firstname("test")
                                .gender(Gender.d)
                                .createdOn(LocalDate.EPOCH)
                                .build()
                )
        );


    }

}
