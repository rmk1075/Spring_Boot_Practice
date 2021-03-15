package com.example.springbootpractice.web;

import com.example.springbootpractice.dto.PostDto;
import com.example.springbootpractice.service.LoginService;
import com.example.springbootpractice.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class BlogController {
    @Autowired
    PostService postService;

    @Autowired
    LoginService loginService;

    @GetMapping({"/", "/blog"})
    public String getBlog(Model model) {
        List<PostDto> postList = postService.getAllPost();
//        model.addAttribute("postList", postList);

        // obj: {title, contents, crtnDate, userName}
        List<Object[]> list = new ArrayList<>();
        for(PostDto post : postList) {
            try {
                Object[] obj = {post.getTitle(), post.getCrtnDate(), loginService.getUserInfo(post.getAuthorId()).getName(), post.getContents()};
                list.add(obj);
            } catch (Exception e) {
                System.out.println("author Id: " + post.getAuthorId());
            }
        }
        model.addAttribute("postList", list);

        return "blog/index";
    }

    @GetMapping("/post")
    public String getPost(HttpServletRequest request) {
        HttpSession session = request.getSession();

        // 사용자 정보가 없는 경우 - 로그인을 하지 않은 경우
        if(session.getAttribute("userId") == null) {
            return "redirect:/";
        }

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
