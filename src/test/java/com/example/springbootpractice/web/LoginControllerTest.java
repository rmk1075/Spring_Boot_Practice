package com.example.springbootpractice.web;

import com.example.springbootpractice.domain.User;
import com.example.springbootpractice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

        // session 사용자 정보 등록
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("id", user.getId());
        session.setAttribute("userId", user.getUserId());
        session.setAttribute("userName", user.getName());
        session.setAttribute("userEmail", user.getEmail());

        mockMvc.perform(get("/logout").session(session))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(viewName));

        // Session 사용자 정보 삭제 확인
        assert session.getAttribute("id") == null;
        assert session.getAttribute("userId") == null;
        assert session.getAttribute("userName") == null;
        assert session.getAttribute("userEmail") == null;
    }

    @Test
    void postSignIn() throws Exception {
        String userId = "test02";
        String userPwd = "test02";
        String viewName = "redirect:/";

        // 로그인 session 등록
        MockHttpSession session = new MockHttpSession();
        mockMvc.perform(post("/signin").session(session).param("id", userId).param("password", userPwd))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(viewName));

        // session 확인
        assert session.getAttribute("userId").equals(userId);

        // 존재하지 않는 계정 로그인 테스트
        session = new MockHttpSession();
        userId = "fake Id";
        userPwd = "fake Password";
        viewName = "login/sign-in";
        mockMvc.perform(post("/signin").session(session).param("id", userId).param("password", userPwd))
                .andExpect(status().isOk())
                .andExpect(view().name(viewName));
    }

    @Test
    void postSignUp() throws Exception {
        String viewName = "redirect:/";

        MultiValueMap<String, String> user = new LinkedMultiValueMap<>();
        user.add("id", "mockId");
        user.add("password", "mockPwd");
        user.add("name", "mockName");
        user.add("email", "mock@email.com");

        // 회원가입 테스트
        mockMvc.perform(post("/signup").params(user))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(viewName));

        // 가입된 회원 정보 테스트
        User userInfo = userRepository.findByUserId(user.get("id").get(0));
        assert userInfo.getUserId().equals(user.get("id").get(0));
        assert userInfo.getPassword().equals(user.get("password").get(0));
        assert userInfo.getName().equals(user.get("name").get(0));
        assert userInfo.getEmail().equals(user.get("email").get(0));

        // 이미 등록된 회원 정보로 재가입 테스트
        viewName = "/login/sign-up";
        mockMvc.perform(post("/signup").params(user))
                .andExpect(status().isOk())
                .andExpect(view().name(viewName));

        // 회원정보 삭제
        userRepository.deleteById(userInfo.getId());

        // 삭제 확인
        assert userRepository.findByUserId(user.get("id").get(0)) == null;
    }
}