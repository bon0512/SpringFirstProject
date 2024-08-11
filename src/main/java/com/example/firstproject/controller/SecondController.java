package com.example.firstproject.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecondController {

    @GetMapping("/random-quote")
    public String randomQuote(Model model){
        String[] quotes = {
                "행복은 습관이다. 그것을 몸에 지니라. "+"-허버드",
                "고개를 숙이지 마십시오. 세상을 똑바로 정면으로 "+ " 바라보십시오. -헬렌켈러-",
                "당신이 할 수 있다고 믿든 할 수 없다고 믿든 "+" 믿는대로 될것이다 - 헨리포드-"
        };

        int randInt=(int)(Math.random()* quotes.length);

        model.addAttribute("randomQuote",quotes[randInt]);
        return "random-quote";

    }
}
