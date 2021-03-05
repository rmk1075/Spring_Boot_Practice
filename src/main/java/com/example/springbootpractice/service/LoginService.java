package com.example.springbootpractice.service;

import com.example.springbootpractice.dto.UserDto;

public interface LoginService {
    public UserDto getUserInfo(String userId);
    public void createUserInfo(UserDto userDto);
}
