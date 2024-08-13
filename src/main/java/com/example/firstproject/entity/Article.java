package com.example.firstproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@ToString
@NoArgsConstructor
@Getter
public class Article {

    @Id
    @GeneratedValue // ID 생성 전략 명시 (필수는 아니지만 권장)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    // 기본 생성자 (JPA가 필요로 함) @NoArgsConstructor로 대체함


    // 모든 필드를 초기화하는 생성자
    public Article(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }


    public Long getId() {
        return id;
    }
}
