package com.example.springbootpractice.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlogController {
    @GetMapping("/blog")
    public String getBlog() {
        return "blog/index";
    }
}
