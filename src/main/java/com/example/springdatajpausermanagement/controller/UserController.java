package com.example.springdatajpausermanagement.controller;

import com.example.springdatajpausermanagement.dto.ConverterDto;
import com.example.springdatajpausermanagement.dto.UserResponse;
import com.example.springdatajpausermanagement.entity.User;
import com.example.springdatajpausermanagement.exception.ApiRequestException;
import com.example.springdatajpausermanagement.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController{
    Logger logger = LoggerFactory.getLogger(UserController.class);

    ResponseEntity<UserResponse> response = null;
    ResponseEntity<List<UserResponse>> listResponse;
    UserResponse dtoResponse = null;
    List<UserResponse> userListResult = null;
    User userResult = null;

    @Autowired
    private UserService userService;

    @Autowired
    ModelMapper modelMapper;
    
    @Autowired
    private ConverterDto converterDto;

    @RequestMapping(value = "/users",method = RequestMethod.POST)
    public ResponseEntity<UserResponse> createUser(@RequestBody User user) {
        logger.info("Requested to create user [ username:{}, email:{} ]" , new Object[]{user.getUsername(),user.getEmail()});
        try {
            userResult = userService.createUser(user);
            // convert entity to DTO
            dtoResponse =  converterDto.convertToDto(userResult);
            response =ResponseEntity.ok().body(dtoResponse);
            logger.info("User created successfully");
        } catch (Exception e) {
            logger.error("Error in creating user", e);
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(dtoResponse);
           //throw new ApiRequestException("User Id or Email must not be null");
        }
        logger.info("Response from create user [ status:{}, body:{} ]" , new Object[]{response.getStatusCode(),response.getBody()});
        return response;
    }

    @RequestMapping(value = "/users",method = RequestMethod.PUT)
    public ResponseEntity<UserResponse> editUser(@RequestBody User user) {
        logger.info("Requested to edit user [ id:{}, email:{} ]" , new Object[]{user.getId(),user.getEmail()});
        dtoResponse = null;
        try {
            userResult = userService.editUser(user);
            dtoResponse =  converterDto.convertToDto(userResult);
            response =ResponseEntity.ok().body(dtoResponse);
            logger.info("User edited successfully");
        } catch (Exception e) {
            logger.error("Error in editing user", e);
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(dtoResponse);
        }
        logger.info("Response from edit user [ status:{}, body:{} ]" , new Object[]{response.getStatusCode(),response.getBody()});
        return response;
    }

    @RequestMapping(value = "/users/{id}",method = RequestMethod.GET)
    public ResponseEntity<UserResponse> searchUserById(@PathVariable int id) {
        logger.info("Requested to search user [ id:{}]" , id);
        dtoResponse = null;
        try {
            userResult = userService.searchUser(id);
            dtoResponse = converterDto.convertToDto(userResult);
            response = ResponseEntity.ok().body(dtoResponse);
            logger.info("User search successfully");
        } catch (Exception e) {
            logger.error("Error in searching user", e);
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(dtoResponse);
//            throw new ApiRequestException("User Id or Email must not be null");
        }
        logger.info("Response from search user [ status:{}, body:{} ]" , new Object[]{response.getStatusCode(),response.getBody()});
        return response;
    }

    @RequestMapping(value = "/users/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<UserResponse> deleteUser(@PathVariable int id){
        logger.info("Requested to delete user [ id:{} ]", id);
        try {
            userResult =  userService.deleteUser(id);
            dtoResponse = modelMapper.map(userResult, UserResponse.class);
            response = ResponseEntity.ok().body(dtoResponse);
            logger.info("User deleted successfully");
        } catch (Exception e) {
            logger.error("User Id does not exist", e);
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(dtoResponse);
        }
        logger.info("Response from delete user [ status:{}, body:{} ]" , new Object[]{response.getStatusCode(),response.getBody()});
        return response;
    }

    @RequestMapping(value = "/users",method = RequestMethod.GET)
    public ResponseEntity<List<UserResponse>> viewUsers(){
        try {
            userListResult =  userService.viewUser().stream().map(user -> modelMapper.map(user, UserResponse.class))
                    .collect(Collectors.toList());
            listResponse = ResponseEntity.ok().body(userListResult);
        } catch (Exception e) {
            logger.error("User Id does not exist",e.getMessage());
            listResponse = ResponseEntity.status(HttpStatus.NOT_FOUND).body(userListResult);

        }
        logger.info("Response from view user [ status:{}, body:{} ]" , new Object[]{listResponse.getStatusCode(),listResponse.getBody()});
        return listResponse;
    }
}
