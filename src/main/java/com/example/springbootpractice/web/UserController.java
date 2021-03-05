package com.example.springbootpractice.web;

import com.example.springbootpractice.domain.User;
import com.example.springbootpractice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/list")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/add")
    public @ResponseBody String addUser(@RequestParam String name, @RequestParam String email, @RequestParam String id, @RequestParam String password) {
        User user = new User(name, email, id, password);
        userRepository.save(user);
        return String.format("user %s (email: %s) is saved", name, email);
    }
}
