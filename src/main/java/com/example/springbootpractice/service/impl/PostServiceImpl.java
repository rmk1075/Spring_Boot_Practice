package com.example.springbootpractice.service.impl;

import com.example.springbootpractice.domain.Post;
import com.example.springbootpractice.dto.PostDto;
import com.example.springbootpractice.repository.PostRepository;
import com.example.springbootpractice.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Override
    public PostDto getPostDetail(Long id) {
        Optional<Post> post = postRepository.findById(id);
        return post.map(PostDto::new).orElse(null);
    }

    @Override
    public List<PostDto> getAllPost() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(post -> new PostDto(post)).collect(Collectors.toList());
    }

    @Override
    public boolean addPost(PostDto postDto) {
        postRepository.save(new Post(postDto.getTitle(), postDto.getAuthorId(), postDto.getContents(), postDto.getCrtnDate(), postDto.getChgDate()));
        return true;
    }
}
