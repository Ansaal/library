package de.jhulsch.library.web.rest.v1;

import de.jhulsch.library.service.BookService;
import de.jhulsch.library.service.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${APIV1}/books")
public class BookRest {

    private BookService bookService;

    @Autowired
    public BookRest(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(path = "/",produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Book> getAvailableBooks() {
        return this.bookService.getAvailableBooks().collect(Collectors.toList());
    }

    @GetMapping(path ="/borrows", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Book> getBooksFromUserBetweenDates(@RequestParam(name = "userId") String userId, @RequestParam(name = "from") LocalDate from, @RequestParam(name = "to") LocalDate to) {
        UUID id = UUID.fromString(userId);
        return this.bookService.getBooksFromBetweenDates(id, from,to).collect(Collectors.toList());
    }


}
