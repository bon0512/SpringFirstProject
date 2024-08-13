package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.beans.ConstructorProperties;

//@AllArgsConstructor //생성자 자동생성
@ToString //toString 자동생성
public class ArticleForm {
    private String title;
    private String content;



    @ConstructorProperties({"title","content"})
    public ArticleForm(String title, String content) {
        this.title = title;
        this.content = content;
    }
    public Article toEntity() {
        return new Article(null,title,content);
    }
}
