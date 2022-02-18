package com.example.springdatajpausermanagement.service;

import com.example.springdatajpausermanagement.dao.UserRepository;
import com.example.springdatajpausermanagement.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class UserServiceTest {

    @MockBean
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

//    User user = new User(1,"Jon","Snow","jon@gmail.com");
    User user = new User(1012,"Neville","Harker","nvl@gmail.com");

//    User userTest = new User(0,"Jon","Snow","jon@gmail.com");


    @Test
    void createUser() {
        Mockito.when(userRepository.save(user)).thenReturn(user);
        assertEquals(user,userService.createUser(user));
    }

    @Test
    void editUser() throws Exception{
        User usern = new User(1,"Shane","Cruise","shane@gmail.com");

        Mockito.when(userRepository.findById(user.getId())).thenReturn(Optional.ofNullable(user));
        Mockito.when(userRepository.save(user)).thenReturn(usern);

        assertEquals(usern.getId(),userService.editUser(user).getId());
        assertNotEquals(user.getFirstName(),userService.editUser(user).getFirstName());

    }

    @Test
    void searchUser() throws Exception{

        Mockito.when(userRepository.findById(user.getId())).thenReturn(Optional.ofNullable(user));

        assertEquals(user,userService.searchUser(user.getId()));
        assertEquals(user.getFirstName(),userService.searchUser(user.getId()).getFirstName());
    }

    @Test
    void deleteUser() throws Exception {
        Mockito.when(userRepository.findById(user.getId())).thenReturn(Optional.ofNullable(user));
//        Mockito.when(userRepository.deleteById(user.getId())).thenReturn(user);

        assertEquals(user,userService.searchUser(user.getId()));

    }

    @Test
    void viewUser() {
        User u1 = new User(105,"Jon","Snow","snow@gmail.com");
        User u2 = new User(106,"Hanna","Michele","hanna@gmail.com");
        User u3 = new User(107,"Neville","Dian","dian@gmail.com");

        List<User> userlist = new ArrayList<>(Arrays.asList(u1,u2,u3));

        Mockito.when(userRepository.findAll()).thenReturn(userlist);

        assertEquals(userlist,userService.viewUser());

    }

}