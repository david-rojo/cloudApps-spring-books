package com.cloudapps.springbooks.services.impl;

import static org.springframework.util.CollectionUtils.isEmpty;

import java.util.Collection;
import java.util.stream.Collectors;

import org.dozer.Mapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cloudapps.springbooks.dtos.requests.UpdateUserEmailRequestDto;
import com.cloudapps.springbooks.dtos.requests.UserRequestDto;
import com.cloudapps.springbooks.dtos.responses.UserResponseDto;
import com.cloudapps.springbooks.exceptions.UserCanNotBeDeletedException;
import com.cloudapps.springbooks.exceptions.UserNotFoundException;
import com.cloudapps.springbooks.exceptions.UserWithSameUsernameException;
import com.cloudapps.springbooks.models.User;
import com.cloudapps.springbooks.repositories.UserRepository;
import com.cloudapps.springbooks.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    private Mapper mapper;
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(Mapper mapper, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Collection<UserResponseDto> findAll() {
        return this.userRepository.findAll().stream()
                .map(user -> this.mapper.map(user, UserResponseDto.class))
                .collect(Collectors.toList());
    }

    public UserResponseDto save(UserRequestDto userRequestDto) {
        if (this.userRepository.existsByUsername(userRequestDto.getUsername())) {
            throw new UserWithSameUsernameException();
        }
        User user = this.mapper.map(userRequestDto, User.class);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user = this.userRepository.save(user);
        return this.mapper.map(user, UserResponseDto.class);
    }

    public UserResponseDto findById(long userId) {
        User user = this.userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return this.mapper.map(user, UserResponseDto.class);
    }

    public UserResponseDto updateEmail(long userId, UpdateUserEmailRequestDto updateUserEmailRequestDto) {
        User user = this.userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        if (!user.getEmail().equalsIgnoreCase(updateUserEmailRequestDto.getEmail())) {
            user.setEmail(updateUserEmailRequestDto.getEmail());
            user = this.userRepository.save(user);
        }
        return this.mapper.map(user, UserResponseDto.class);
    }

    public UserResponseDto delete(long userId) {
        User user = this.userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        if (!isEmpty(user.getComments())) {
            throw new UserCanNotBeDeletedException();
        }
        this.userRepository.delete(user);
        return this.mapper.map(user, UserResponseDto.class);
    }

}
