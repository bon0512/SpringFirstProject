package com.example.firstproject.api;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class ArticleApiController {

    @Autowired
    private ArticleRepository articleRepository;

    //GET
    @GetMapping("/api/articles")
    public List<Article> index(){
        return articleRepository.findAll();
    }

    @GetMapping("/api/articles/{id}") //단일 조회
    public Article index(@PathVariable("id") Long id){
        return articleRepository.findById(id).orElse(null);
    }

    //POST

    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm form){
        Article article = form.toEntity();

        return articleRepository.save(article);

    }


    //PATCH

    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable("id") Long id, @RequestBody ArticleForm form){
        // 1.DTD->엔티티 변환하기
        Article article = form.toEntity();
        log.info("id: {},article: {}", id, article.toString());
        // 2.타깃 조회하기
        Article target = articleRepository.findById(id).orElse(null);

        // 3. 잘못된 업데이트 요청 처리하기

        if(target==null||id!=article.getId()){
            //400 잘못된 요청 응답!
            log.info("잘못된 요청! id:{},article : {} ",id,article.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }



        // 4. 업데이트 및 정상 응답(200)하기

        target.patch(article);
        Article updated = articleRepository.save(target);
        return ResponseEntity.status(HttpStatus.OK).body(updated);  //정상 응답


    }


    //DELETE
}
