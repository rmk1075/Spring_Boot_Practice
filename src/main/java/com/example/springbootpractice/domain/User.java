package com.example.springbootpractice.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String userId;
    private String password;

    protected User() {}

    public User(String name, String email, String userId, String password) {
        this.name = name;
        this.email = email;
        this.userId = userId;
        this.password = password;
    }
}
