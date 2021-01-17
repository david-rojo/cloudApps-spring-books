package com.cloudapps.springbooks.services;

import java.util.Collection;

import com.cloudapps.springbooks.dtos.requests.UpdateUserEmailRequestDto;
import com.cloudapps.springbooks.dtos.requests.UserRequestDto;
import com.cloudapps.springbooks.dtos.responses.UserResponseDto;

public interface UserService {

    Collection<UserResponseDto> findAll();

    UserResponseDto save(UserRequestDto userRequestDto);

    UserResponseDto findById(long userId);

    UserResponseDto updateEmail(long userId, UpdateUserEmailRequestDto updateUserEmailRequestDto);

    UserResponseDto delete(long userId);
    
    UserResponseDto findByUsername(String username);

}
