package com.example.springdatajpausermanagement.service;

import com.example.springdatajpausermanagement.entity.User;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserService {
    User createUser(@RequestBody User user) throws Exception;

    User editUser(@RequestBody User user) throws Exception;

    User searchUser(int id) throws Exception;

    User deleteUser(int id) throws Exception;

    List<User> viewUser() throws Exception;
}
