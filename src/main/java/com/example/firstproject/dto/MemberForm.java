package com.example.firstproject.dto;

import com.example.firstproject.entity.Member;

import java.beans.ConstructorProperties;

public class MemberForm {

    private String email;
    private String password;

    @ConstructorProperties({"email","password"})
    public MemberForm(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "MemberFrom{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Member toEntity() {
        return new Member(null,email,password);
    }

}
