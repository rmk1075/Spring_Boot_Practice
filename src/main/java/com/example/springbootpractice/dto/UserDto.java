package com.example.springbootpractice.dto;

import com.example.springbootpractice.domain.User;
import lombok.Getter;

@Getter
public class UserDto {
    private String name;
    private String email;
    private String userId;
    private String password;

    public UserDto(User entity) {
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.userId = entity.getUserId();
        this.password = entity.getPassword();
    }

    public UserDto(String name, String email, String userId, String password) {
        this.name = name;
        this.email = email;
        this.userId = userId;
        this.password = password;
    }
}
