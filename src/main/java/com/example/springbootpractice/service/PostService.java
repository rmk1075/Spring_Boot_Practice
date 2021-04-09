package com.example.springbootpractice.service;

import com.example.springbootpractice.dto.PostDto;

import java.util.List;

public interface PostService {
    public PostDto getPostDetail(Long id);
    public List<PostDto> getAllPost();
    public boolean addPost(PostDto postDto);
}
