package com.example.springbootpractice.web;

import com.example.springbootpractice.dto.PostDto;
import com.example.springbootpractice.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class BlogController {
    @Autowired
    PostService postService;

    @GetMapping({"/", "/blog"})
    public String getBlog() {
        return "blog/index";
    }

    @GetMapping("/post")
    public String getPost() {
        return "blog/post";
    }

    @PostMapping("/addPost")
    public @ResponseBody boolean addPost(@RequestParam String title, @RequestParam String contents) {
        if(postService.addPost(new PostDto(title, 1L, contents, new Date(), new Date())))
            return true;
        else
            return false;
    }
}
