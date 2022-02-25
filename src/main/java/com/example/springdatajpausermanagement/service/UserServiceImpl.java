package com.example.springdatajpausermanagement.service;

import com.example.springdatajpausermanagement.dao.UserRepository;
import com.example.springdatajpausermanagement.entity.User;
import com.example.springdatajpausermanagement.exception.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.management.InstanceNotFoundException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User createUser(@RequestBody User user) {
        if (user == null || user.getId() == 0 || user.getEmail().length() == 0) {
            throw new ApiRequestException("NotFound");
        }

        User savedUser = null;
        return savedUser;
    }

    @Override
    public User editUser(@RequestBody User user) throws Exception {
        if (user == null || user.getId() == 0) {
            throw new Exception("User or Id must not be null");
        }
        User existingUser = userRepository.findById(user.getId()).orElse(null);
        if (existingUser != null) {
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setEmail(user.getEmail());
            userRepository.save(existingUser);
        }
        return existingUser;
    }

    @Override
    public User searchUser(int id) throws Exception {
        User usrSearch = null;
        if (id > 0) {
            usrSearch = userRepository.findById(id).get();
        }
        return usrSearch;
    }

    @Override
    public User deleteUser(int id) throws Exception {
        User usrSearch = userRepository.findById(id).orElse(null);
        if (!userRepository.findById(id).isPresent()) {
            throw new Exception("Not found");
        }
        userRepository.deleteById(id);
        return usrSearch;
    }

    @Override
    public List<User> viewUser() throws Exception {
        List<User> usrList = userRepository.findAll();
        if (usrList.isEmpty()) {
            throw new Exception("User list is empty");
        }
        return usrList;
    }
}
