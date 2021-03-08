package com.example.springbootpractice.repository;

import com.example.springbootpractice.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Post findByTitle(String title);
    Post findByAuthorId(Long authorId);
}
