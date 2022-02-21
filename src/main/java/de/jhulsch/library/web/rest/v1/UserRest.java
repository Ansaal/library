package de.jhulsch.library.web.rest.v1;

import de.jhulsch.library.service.UserService;
import de.jhulsch.library.service.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${APIV1}/users")
public class UserRest {

    private final UserService userService;

    @Autowired
    public UserRest(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/borrows",produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<User> getUsersWithBorrowsOnDate(@RequestParam(name = "date",required = false)LocalDate date) {
        if(date==null) {
            date=LocalDate.now();
        }
        return userService.findAllBorrowersAtDate(date).collect(Collectors.toSet());
    }

    @GetMapping(path = "/",produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<User> getUsersWithoutBorrows() {
        return userService.findAllActiveUsersWithoutBook().collect(Collectors.toSet());
    }


}
