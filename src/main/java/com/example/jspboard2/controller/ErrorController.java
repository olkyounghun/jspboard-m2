package com.example.jspboard2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController {

    @GetMapping("/errorMove2")
    public ModelAndView errorMove(@ModelAttribute("errorMove") Object errorMove){
        ModelAndView mv = new ModelAndView();

        return mv;
    }
}
