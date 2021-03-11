package com.example.springbootpractice.web;

import com.example.springbootpractice.domain.User;
import com.example.springbootpractice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class LoginControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;

    @Test
    void getSignIn() throws Exception {
        String viewName = "login/sign-in";

        mockMvc.perform(get("/signin"))
                .andExpect(status().isOk())
                .andExpect(view().name(viewName));
    }

    @Test
    void getSignUp() throws Exception {
        String viewName = "login/sign-up";

        mockMvc.perform(get("/signup"))
                .andExpect(status().isOk())
                .andExpect(view().name(viewName));
    }

    @Test
    void getLogout() throws Exception {
        String viewName = "redirect:/";
        String userId = "test02";
        User user = userRepository.findByUserId(userId);

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("user", user);

        mockMvc.perform(get("/logout").session(session))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(viewName));

        assert session.getAttribute("user") == null;
    }
}