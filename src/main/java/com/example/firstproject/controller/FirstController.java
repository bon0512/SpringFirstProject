package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/greeings")
    public String niceToMeetYou(Model model){
        model.addAttribute("username","hongpark");
        return "greetings";
    }

    @GetMapping("/bye")
    public String seeYouNextTime(Model model){
        model.addAttribute("username","hongpark");
        return "goodbye";
    }
}
