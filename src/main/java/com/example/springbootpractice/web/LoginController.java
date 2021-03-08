package com.example.springbootpractice.web;

import com.example.springbootpractice.dto.UserDto;
import com.example.springbootpractice.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @GetMapping("/logout")
    public String getLogout(Model model) {
        model.addAttribute("user", null);
        return "blog/index";
    }

    /**
     * 로그인시 사용되는 메서드
     * 로그인 성공 시 blog/index 페이지로 이동
     * 로그인 실패 시 login/sign-in 페이지로 이동하여 로그인 재시도
     * @param id
     * @param password
     * @return
     */
    @PostMapping("/signin")
    public String postSignIn(@RequestParam String id, @RequestParam String password, Model model) {
        UserDto userDto = loginService.getUserInfo(id);
        if(userDto == null || !userDto.getPassword().equals(password)) {
            model.addAttribute("user", null);
            return "login/sign-in";
        }

        model.addAttribute("user", userDto);
        return "blog/index";
    }

    /**
     * 회원가입시 사용되는 메서드
     * 회원 가입 성공 시 blog/index 페이지로 이동
     * 회원 가입 실패 시 login/sign-up 페이지로 이동하여 회원가입 재시도
     * @param name
     * @param email
     * @param id
     * @param password
     * @return
     */
    @PostMapping("/signup")
    public String postSignUp(@RequestParam String name, @RequestParam String email, @RequestParam String id, @RequestParam String password) {
        if(loginService.createUserInfo(new UserDto(name, email, id, password)))
            return "blog/index";
        else
            return "login/sign-up";
    }
}
