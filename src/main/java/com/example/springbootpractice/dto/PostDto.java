package com.example.springbootpractice.dto;

import com.example.springbootpractice.domain.Post;
import lombok.Getter;

import java.util.Date;

@Getter
public class PostDto {
    private String title;
    private Long authorId;
    private String contents;
    private Date crtnDate;
    private Date chgDate;

    public PostDto(Post entity) {
        this.title = entity.getTitle();
        this.authorId = entity.getAuthorId();
        this.contents = entity.getContents();
        this.crtnDate = entity.getCrtnDate();
        this.chgDate = entity.getChgDate();
    }

    public PostDto(String title, Long authorId, String contents, Date crtnDate, Date chgDate) {
        this.title = title;
        this.authorId = authorId;
        this.contents = contents;
        this.crtnDate = crtnDate;
        this.chgDate = chgDate;
    }
}
