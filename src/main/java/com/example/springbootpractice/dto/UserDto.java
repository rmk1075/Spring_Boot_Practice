package com.example.springbootpractice.dto;

import com.example.springbootpractice.domain.User;
import lombok.Getter;

import java.util.Date;

@Getter
public class UserDto {
    private String name;
    private String email;
    private String userId;
    private String password;
    private Date crtnDate;
    private Date chgDate;

    public UserDto(User entity) {
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.userId = entity.getUserId();
        this.password = entity.getPassword();
        this.crtnDate = entity.getCrtnDate();
        this.chgDate = entity.getChgDate();
    }

    public UserDto(String name, String email, String userId, String password, Date crtnDate, Date chgDate) {
        this.name = name;
        this.email = email;
        this.userId = userId;
        this.password = password;
        this.crtnDate = crtnDate;
        this.chgDate = chgDate;
    }
}
