package com.example.springdatajpausermanagement.controller;

import com.example.springdatajpausermanagement.entity.User;
import com.example.springdatajpausermanagement.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @RequestMapping(value = "/users",method = RequestMethod.POST)
    public User createUser(@RequestBody User user){
        logger.error("user created");
        return userService.createUser(user);
    }

    @RequestMapping(value = "/users",method = RequestMethod.PUT)
    public User editUser(@RequestBody User user) throws  Exception{
        logger.trace("user edited");
        return userService.editUser(user);
    }

    @RequestMapping(value = "/users/{id}",method = RequestMethod.GET)
    public User searchUserById(@PathVariable int id) throws  Exception{
        logger.trace("user found");
        return userService.searchUser(id);
    }

    @RequestMapping(value = "/users/{id}",method = RequestMethod.DELETE)
    public User deleteUser(@PathVariable int id) throws  Exception{
        logger.trace("user deleted");
        return userService.deleteUser(id);

    }

    //view users
    @RequestMapping(value = "/users",method = RequestMethod.GET)
    public List<User> viewUsers(){
        logger.trace("users viewed");
        return userService.viewUser();
    }




}
