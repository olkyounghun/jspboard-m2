package com.example.jspboard2.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController {

    @GetMapping("/errorMove")
    public ModelAndView errorMove(@Param("errorMove") Object errorMove){
        ModelAndView mv = new ModelAndView();

        String address = String.valueOf(errorMove);

        mv.setViewName(address);
        return mv;
    }
}
