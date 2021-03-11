package com.example.springbootpractice.web;

import com.example.springbootpractice.domain.Post;
import com.example.springbootpractice.domain.User;
import com.example.springbootpractice.repository.PostRepository;
import com.example.springbootpractice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BlogControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

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

    @Test
    void addPost() throws Exception {
        User user = userRepository.findByUserId("test02");

        // 사용자 id 세션 등록
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("id", user.getId());

        // addPost 파라미터 생성 (제목, 내용)
        String title = "testTitle";
        String contents = "test contents. 게시물 생성 테스트 내용";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("title", title);
        params.add("contents", contents);

        String viewName = "redirect:/";

        // 게시물 작성 - 세션, 파라미터 등록
        mockMvc.perform(post("/addPost").session(session).params(params))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(viewName));

        // 생성된 게시물 테스트
        Post post = postRepository.findByTitle(title);
        assert post.getTitle().equals(title);
        assert post.getContents().equals(contents);
        assert post.getAuthorId().equals(user.getId());

        // 생성된 게시물 삭제
        postRepository.deleteById(post.getId());

        // 게시물 삭제 확인
        assert postRepository.findById(post.getId()).isEmpty();
    }
}