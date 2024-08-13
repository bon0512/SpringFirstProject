package com.example.firstproject.controller;

import com.example.firstproject.dto.MemberForm;
import com.example.firstproject.entity.Member;
import com.example.firstproject.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@Slf4j
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/signup")
    String singUpPage(){
        return "members/new";
    }

    @PostMapping("/join")
    public String createMember(MemberForm memberForm){
        log.info(memberForm.toString());
        Member member = memberForm.toEntity();
        log.info(member.toString());


        //2.리파지터리로 엔티티를 DB에 저장
        Member saved = memberRepository.save(member);
        log.info(saved.toString());

        return "redirect:/articles/" +saved.getId();
    }

    @GetMapping("/members/{id}")
    public String show(@PathVariable("id") Long id, Model model){
        //1.리파지터를 이용해 데이터 엔티티 가져오기
        Member byId = memberRepository.findById(id).orElse(null);


        //2. 모델을 이용해 뷰로 출력할 준비하기

        model.addAttribute("byId",byId);

        //3. 뷰 페이지 반환

        return "members/show";
    }


    @GetMapping("/members")
    public String index(Model model){

        ArrayList<Member> all = memberRepository.findAll();


        model.addAttribute("all",all);



        return "members/index";
    }
}
