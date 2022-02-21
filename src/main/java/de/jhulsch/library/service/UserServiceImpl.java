package de.jhulsch.library.service;

import de.jhulsch.library.persistence.repository.UserRepository;
import de.jhulsch.library.service.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.stream.Stream;

@Component
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Stream<User> findAllActiveUsersWithoutBook() {
        return this.userRepository.findAllActiveUsersWithoutBook();
    }

    @Override
    public Stream<User> findAllBorrowersAtDate(LocalDate date) {
        return this.userRepository.findAllBorrowersAtDate(date);
    }
}
