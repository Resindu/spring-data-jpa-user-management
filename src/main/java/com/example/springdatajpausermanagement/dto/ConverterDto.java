package com.example.springdatajpausermanagement.dto;


import com.example.springdatajpausermanagement.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConverterDto {

    @Autowired
    private ModelMapper modelMapper;

    public UserResponse convertToDto(User user){
        return modelMapper.map(user, UserResponse.class);

    }
}
