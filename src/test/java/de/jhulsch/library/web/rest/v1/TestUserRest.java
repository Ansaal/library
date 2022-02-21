package de.jhulsch.library.web.rest.v1;

import de.jhulsch.library.LibraryApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {LibraryApplication.class})
@AutoConfigureMockMvc
class TestUserRest {

    @Autowired
    MockMvc mockMvc;


    @Test
    void testGetUsersWithoutBorrows() throws Exception {

     this.mockMvc.perform(get("/api/v1/users/")).andDo(print()).andExpect(status().isOk())
             .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    void getBooksFromUserBetweenDates() throws Exception {
        this.mockMvc.perform(get("/api/v1/users/borrows?date=2012-03-04")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
    @Test
    void getBooksFromUserNow() throws Exception {
        this.mockMvc.perform(get("/api/v1/users/borrows")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

}
