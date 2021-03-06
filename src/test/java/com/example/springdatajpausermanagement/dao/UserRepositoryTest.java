package com.example.springdatajpausermanagement.dao;

import com.example.springdatajpausermanagement.entity.User;
import org.junit.jupiter.api.Assertions;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {
    List<User> usersList = new ArrayList<>();


    @Autowired
    private UserRepository userRepository;

    @Test
    public void createUserTest() {
        User u1 = new User(1012, "Neville", "Harker", "nvl@gmail.com", "neville", "nev123");

        userRepository.save(u1);
        assertTrue(userRepository.existsById(u1.getId()));
        assertTrue(userRepository.getById(u1.getId()).getId() > 0);

    }

    @Test
    public void searchUserTest() {
        User user = userRepository.findById(108).orElse(null);
        assertEquals(108, user.getId());
    }

    @Test
    public void viewAllUsersTest() {
        usersList = userRepository.findAll();
        assertEquals(3, usersList.size());
    }

    @Test
    public void updateUserTest() {
        User user = userRepository.findById(108).get();
        user.setEmail("r@gmail.com");
        User updateUser = userRepository.save(user);
        assertEquals("r@gmail.com", updateUser.getEmail());
    }

    @Test
    public void deleteUserTest() {
        User user = userRepository.findById(108).get();
        userRepository.deleteById(108);

        assertEquals(2, userRepository.findAll().size());

    }

}