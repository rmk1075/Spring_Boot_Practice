package com.example.springbootpractice.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BlogControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void getBlog() throws Exception {
        String viewName = "blog/index";

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name(viewName));

        mockMvc.perform(get("/blog"))
                .andExpect(status().isOk())
                .andExpect(view().name(viewName));
    }

    @Test
    void getPost() throws Exception {
        String viewName = "blog/post";

        mockMvc.perform(get("/post"))
                .andExpect(status().isOk())
                .andExpect(view().name(viewName));
    }
}