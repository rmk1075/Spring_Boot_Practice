package com.example.springbootpractice.web;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginControllerTest {

    @Test
    void getSignIn() {
        assertEquals(new LoginController().getSignIn(), "login/sign-in");
    }

    @Test
    void postSignIn() {
        assertEquals("login/sign-in", new LoginController().postSignIn("test", "test"));
    }
}