package com.example.firstproject.dto;

import com.example.firstproject.entity.Member;
import lombok.ToString;

import java.beans.ConstructorProperties;

@ToString
public class MemberForm {
    private Long id;
    private String email;
    private String password;

    @ConstructorProperties({"id","email","password"})
    public MemberForm(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }



    public Member toEntity() {
        return new Member(id,email,password);
    }

}
