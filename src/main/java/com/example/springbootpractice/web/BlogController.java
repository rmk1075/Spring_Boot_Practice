package com.example.springbootpractice.web;

import com.example.springbootpractice.dto.PostDto;
import com.example.springbootpractice.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

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
    public String addPost(@RequestParam String title, @RequestParam String contents, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        if(postService.addPost(new PostDto(title, (Long)session.getAttribute("id"), contents, new Date(), new Date())))
            return "redirect:/";
        else
            throw new Exception();
    }
}
