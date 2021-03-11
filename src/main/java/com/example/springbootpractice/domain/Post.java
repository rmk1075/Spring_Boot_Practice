package com.example.springbootpractice.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private Long authorId;
    private String contents;
    private Date crtnDate;
    private Date chgDate;

    public Post() {}

    public Post(String title, Long authorId, String contents, Date crtnDate, Date chgDate) {
        this.title = title;
        this.authorId = authorId;
        this.contents = contents;
        this.crtnDate = crtnDate;
        this.chgDate = chgDate;
    }
}
