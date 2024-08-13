package com.example.firstproject.dto;

import com.example.firstproject.entity.Member;
import lombok.ToString;

import java.beans.ConstructorProperties;

@ToString
public class MemberForm {

    private String email;
    private String password;

    @ConstructorProperties({"email","password"})
    public MemberForm(String email, String password) {
        this.email = email;
        this.password = password;
    }



    public Member toEntity() {
        return new Member(null,email,password);
    }

}
