package com.example.springdatajpausermanagement.controller;

import com.example.springdatajpausermanagement.entity.User;
import com.example.springdatajpausermanagement.service.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        //MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
//        System.out.println("Running: tearDown");
        userService = null;
        //assertNull(userService);
        //cleanup after each method
    }


    User u1 = new User(105, "Jon", "Snow", "snow@gmail.com", "neville", "nev123");
    User u2 = new User(106, "Hanna", "Michele", "hanna@gmail.com", "neville", "nev123");
    User u3 = new User(107, "Neville", "Dian", "dian@gmail.com", "neville", "nev123");
    User u4 = new User(0, "Manu", "Rios", "vb@gmail.com", "neville", "nev123");
    User u5 = null;
    @Test
    void createUser() throws Exception{
        Mockito.when(userService.createUser(any(User.class))).thenReturn(u4);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/users")
                        .content(new ObjectMapper().writeValueAsString(u4))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$",notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Manu"));
        Mockito.verify(userService).createUser(u4);

    }

    @Test
    void editUser() {
        User u4 = new User(107, "Anu", "Rios", "manu@gmail.com", "neville", "nev123");

        try {
            Mockito.when(userService.searchUser(10)).thenReturn(u1);
            Mockito.when(userService.editUser(u5)).thenReturn(u1);

            mockMvc.perform(MockMvcRequestBuilders
                            .put("/users")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
                            .content(new ObjectMapper().writeValueAsString(u4)))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$", notNullValue()))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Jon"));
        } catch (Exception e) {
//            assertEquals("Invalid data", e.getMessage());

        }
    }

    @Test
    void searchUserById() {
        try {
            Mockito.when(userService.searchUser(90)).thenReturn(u1);
            mockMvc.perform(MockMvcRequestBuilders
                            .get("/users/105")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Jon"));
        } catch (Exception e) {
            assertEquals("User Id does not exist", e.getMessage());
        }
    }

    @Test
    public void deleteUser() throws Exception {
            Mockito.when(userService.searchUser(u1.getId())).thenReturn(u1);
            mockMvc.perform(MockMvcRequestBuilders
                            .delete("/users/100")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(print());
    }

    @Test
    public void viewUsers() throws Exception {
        List<User> userList = new ArrayList<>(Arrays.asList(u1, u2, u3));
        try {
            Mockito.when(userService.viewUser()).thenReturn(userList);

            mockMvc.perform(MockMvcRequestBuilders
                            .get("/users")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(3)))
                    .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName").value("Jon"));
        }catch (Exception e){
            assertEquals("User Id does not exist", e.getMessage());

        }
    }
}