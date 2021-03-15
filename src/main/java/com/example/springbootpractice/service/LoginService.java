package com.example.springbootpractice.service;

import com.example.springbootpractice.dto.UserDto;

public interface LoginService {
    public UserDto getUserInfo(Long id);
    public UserDto getUserInfo(String userId);
    public boolean addUser(UserDto userDto);
}
