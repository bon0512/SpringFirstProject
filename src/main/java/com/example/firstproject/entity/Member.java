package com.example.firstproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.ToString;

@Entity
@ToString
public class Member {


    @Id
    @GeneratedValue
    Long id;

    @Column
    String email;

    @Column
    String password;

    // 기본 생성자 (JPA가 필요로 함)
    protected Member() {
    }

    public Member(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }



    public Long getId() {
        return id;
    }
}
