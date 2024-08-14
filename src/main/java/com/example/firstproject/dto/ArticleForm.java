package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.beans.ConstructorProperties;

//@AllArgsConstructor //생성자 자동생성
@ToString //toString 자동생성
public class ArticleForm {
    private Long id;
    private String title;
    private String content;

    @ConstructorProperties({"id", "title", "content"})
    public ArticleForm(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }


    public Article toEntity() {
        return new Article(id,title,content);
    }
}
