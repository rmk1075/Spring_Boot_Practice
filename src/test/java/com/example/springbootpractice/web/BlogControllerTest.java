package com.example.springbootpractice.web;

import com.example.springbootpractice.domain.Post;
import com.example.springbootpractice.domain.User;
import com.example.springbootpractice.dto.PostDto;
import com.example.springbootpractice.repository.PostRepository;
import com.example.springbootpractice.repository.UserRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.stream.Collectors;

import static org.hamcrest.Matchers.hasProperty;
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
        String viewName = "redirect:/";

        mockMvc.perform(get("/post"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(viewName));

        // 로그인 사용자 정보 test02 계정 시용
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("userId", "test02");

        viewName = "blog/post";
        mockMvc.perform(get("/post").session(session))
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

    @Test
    void getPostList() throws Exception {
        String viewName = "blog/list";

        mockMvc.perform(get("/postList"))
                .andExpect(status().isOk())
                .andExpect(view().name(viewName));
    }

    @Test
    void getPostDetail() throws Exception {
        // post list 전체 게시물 대상으로 테스트
        String viewName = "blog/detail";
        for(PostDto post : postRepository.findAll().stream().map(PostDto::new).collect(Collectors.toList())) {
            mockMvc.perform(get("/post/" + post.getId()))
                    .andExpect(status().isOk())
                    .andExpect(view().name(viewName))
                    .andExpect(model().attribute("post", hasProperty("id", Matchers.equalTo(post.getId()))))
                    .andExpect(model().attribute("authorName", userRepository.findById(post.getAuthorId()).get().getName()));
        }

        // 존재하지 않는 게시물 번호에 대한 테스트
        viewName = "redirect:/postList";
        mockMvc.perform(get("/post/-1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(viewName));
    }
}