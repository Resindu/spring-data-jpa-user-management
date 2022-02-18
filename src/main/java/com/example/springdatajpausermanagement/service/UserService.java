package com.example.springdatajpausermanagement.service;

import com.example.springdatajpausermanagement.dao.UserRepository;
import com.example.springdatajpausermanagement.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public User createUser(@RequestBody User user){
        return userRepository.save(user);

    }
    public User editUser(@RequestBody User user) throws Exception{
        if(user == null || user.getId() == 0){
            throw new Exception("User or Id must not be null");
        }
        User existingUser = userRepository.findById(user.getId()).orElse(null);
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        return userRepository.save(existingUser);
    }

    public User searchUser(int id) throws Exception{
        return userRepository.findById(id).orElse(null);
    }
    public User deleteUser(int id) throws Exception{
        User usrSearch = userRepository.findById(id).orElse(null);
        if(usrSearch == null  && usrSearch.getId() ==0){
            throw new Exception("user doesnt exist");

        }
        userRepository.deleteById(id);
        return usrSearch;

    }
    public List<User> viewUser(){
        return userRepository.findAll();
    }
}
