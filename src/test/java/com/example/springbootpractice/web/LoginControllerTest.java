package com.example.springbootpractice.web;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginControllerTest {

    @Test
    void getSignIn() {
        assertEquals(new LoginController().getSignIn(), "login/sign-in");
    }
}