package com.example.springbootpractice.service.impl;

import com.example.springbootpractice.domain.User;
import com.example.springbootpractice.dto.UserDto;
import com.example.springbootpractice.repository.UserRepository;
import com.example.springbootpractice.service.LoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto getUserInfo(String userId) {
        User user = userRepository.findByUserId(userId);

        if(user != null) {
            return new UserDto(user);
        } else {
            return null;
        }
    }

    @Override
    public void createUserInfo(UserDto userDto) {
        userRepository.save(new User(userDto.getName(), userDto.getEmail(), userDto.getUserId(), userDto.getPassword()));
    }
}
