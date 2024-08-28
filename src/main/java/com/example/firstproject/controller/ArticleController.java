package com.example.firstproject.controller;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Controller
@Slf4j
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CommentService commentService;

    @GetMapping("/articles/new")
    String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
        //System.out.println(form.toString());
        log.info(form.toString());
        //1.DTO를 엔티티로 변환
        Article article = form.toEntity();
        log.info(article.toString());

        //2.리파지터리로 엔티티를 DB에 저장
        Article saved = articleRepository.save(article);

        log.info(saved.toString());
        return "redirect:/articles/"+saved.getId();

    }


    @GetMapping("/articles/{id}")
    public String show(@PathVariable("id") Long id, Model model){
        log.info("id = "+id);

        //1. id를 조회에 데이터 가져오기
        Article articleEntity =articleRepository.findById(id).orElse(null);
        List<CommentDto> commentDtos = commentService.comments(id);
        //2. 모델에 데이터 등록하기
        model.addAttribute("articles",articleEntity);
        model.addAttribute("commentDtos",commentDtos);
        //3. 뷰페이지 반환하기
        return "articles/show";
    }

    @GetMapping("/articles")
    //전체 데이터 목록 가져오기
    public String index(Model model){

        //1. 모든 데이터 가져오기 리파지터리를 이용
        ArrayList<Article> articleEntityList = articleRepository.findAll();

        //2. 모델에 데이터 등록하기
        model.addAttribute("articleList",articleEntityList);
        //3. 사용자에게 보여 줄 뷰 페이지 설정하기

        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model){
        //레파지토리를 이용해 수정할 데이터 엔티티로 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);


        //모델에 데이터 등록하기
        model.addAttribute("article",articleEntity);


        //뷰페이지 설정하기
        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form){
        log.info(form.toString());


        //1.DTO를 엔티티로 변환하기
        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString());
        //2.엔티티를 DB에 저장하기
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);


        //2-2 기존 데이터값을 갱신하기
        if(target!=null){
            articleRepository.save(articleEntity);
        }

        //3.수정 결과 페이지로 리다이렉트하기



        return "redirect:/articles/" + articleEntity.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id ,RedirectAttributes rttr){         //삭제 요청 메서드
        log.info("삭제 요청이 들어왔습니다");

        //1. 삭제할 대상 가져오기(리파지터리 이용)
        Article target = articleRepository.findById(id).orElse(null);
        log.info(target.toString());

        //2. 대상 엔티티 삭제하기
        if(target!=null){       //삭제할 대상이 있으면
            articleRepository.delete(target);   //대상을 삭제하기
            rttr.addFlashAttribute("msg","삭제 됬습니다!");
            /*RedirectAttributes 객체로 리다이렉트 페이지에서 사용할 데이터를 남길 수 있다.
            *  addFlashAttribute메서드를 사용하면 리다이렉트 시점에 한번만 사용할 수 있는 데이터를 등록가능
            *   리다이렉트된 페이지에서 설정을해줘야한다 여기서는 index.html*/
        }

        //3. 결과 페이지로 리다이렉트하기
        return "redirect:/articles" ;
    }

}
