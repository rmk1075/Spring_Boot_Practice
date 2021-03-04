package com.example.springbootpractice.web;

import com.example.springbootpractice.domain.User;
import com.example.springbootpractice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/signin")
    public String getSignIn() {
        return "login/sign-in";
    }

    @PostMapping("/signin")
    public String postSignIn(@RequestParam String id, @RequestParam String password) {
        User user = userRepository.findByUserId(id);
        if(user == null || !user.getPassword().equals(password))
            return "login/sign-in";

        return "blog/index";
    }
}
