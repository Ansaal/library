package de.jhulsch.library.web.rest.v1;

import de.jhulsch.library.LibraryApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest(classes = {LibraryApplication.class})
@AutoConfigureMockMvc
class TestBookRest {

    @Autowired
    MockMvc mockMvc;


    @Test
    void testGetAvailableBooks() throws Exception {

     this.mockMvc.perform(get("/api/v1/books/")).andDo(print()).andExpect(status().isOk())
             .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    void getBooksFromUserBetweenDates() throws Exception {
        this.mockMvc.perform(get("/api/v1/books/borrows?from=2012-03-04&to=2020-01-01&userId=02eef79a-da28-4e19-bcdc-30dd0c385e26")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

}
