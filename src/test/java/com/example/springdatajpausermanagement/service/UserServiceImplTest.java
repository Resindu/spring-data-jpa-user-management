package com.example.springdatajpausermanagement.service;

import com.example.springdatajpausermanagement.dao.UserRepository;
import com.example.springdatajpausermanagement.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {

    @MockBean
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    User user = new User(109,"Neville","Harker","nvl@gmail.com","neville","nev123");


    @Test
    void createUser() throws Exception {
        Mockito.when(userRepository.save(user)).thenReturn(user);
        assertEquals(user,userService.createUser(user));
    }

    @Test
    void editUser() throws Exception{
        User userEdited = new User(109,"Shane","Cruise","shane@gmail.com","neville","nev123");

        Mockito.when(userRepository.findById(user.getId())).thenReturn(Optional.ofNullable(user));
        Mockito.when(userRepository.save(user)).thenReturn(userEdited);

        assertEquals(userEdited.getId(),userService.editUser(user).getId());
        assertNotEquals(userEdited.getFirstName(),userService.editUser(user).getFirstName());

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
        //Mockito.when(userRepository.deleteById(user.getId())).thenReturn(user);

        assertEquals(user,userService.searchUser(user.getId()));

    }

    @Test
    void viewUser() throws Exception{
        User u1 = new User(105,"Jon","Snow","snow@gmail.com","neville","nev123");
        User u2 = new User(106,"Hanna","Michele","hanna@gmail.com","neville","nev123");
        User u3 = new User(107,"Neville","Dian","dian@gmail.com","neville","nev123");

        List<User> userList = new ArrayList<>(Arrays.asList(u1,u2,u3));

        Mockito.when(userRepository.findAll()).thenReturn(userList);

        assertEquals(userList,userService.viewUser());

    }

}