package com.example.springbootpractice.web;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelloControllerTest {

    @Test
    void getHello() {
        HelloController hello = new HelloController();
        assertEquals(hello.getHello(), "hello");
    }
}