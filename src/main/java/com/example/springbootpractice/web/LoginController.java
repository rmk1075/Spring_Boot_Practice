package com.example.springbootpractice.web;

import com.example.springbootpractice.dto.UserDto;
import com.example.springbootpractice.service.LoginService;
import com.example.springbootpractice.service.impl.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;

    @GetMapping("/signin")
    public String getSignIn() {
        return "login/sign-in";
    }

    @GetMapping("/signup")
    public String getSignUp() {
        return "login/sign-up";
    }

    @PostMapping("/signin")
    public String postSignIn(@RequestParam String id, @RequestParam String password) {
        UserDto userDto = loginService.getUserInfo(id);
        if(userDto == null || !userDto.getPassword().equals(password))
            return "login/sign-in";

        return "blog/index";
    }

    @PostMapping("/signup")
    public String postSignUp(@RequestParam String name, @RequestParam String email, @RequestParam String id, @RequestParam String password) {
        loginService.createUserInfo(new UserDto(name, email, id, password));
        return "blog/index";
    }
}
