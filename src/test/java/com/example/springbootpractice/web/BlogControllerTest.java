package com.example.springbootpractice.web;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlogControllerTest {

    @Test
    void getBlog() {
        BlogController blogController = new BlogController();
        assertEquals(blogController.getBlog(), "blog/index");
    }
}