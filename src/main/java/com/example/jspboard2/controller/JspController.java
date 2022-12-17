package com.example.jspboard2.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/list")
public class JspController {

    @GetMapping
    public String main(){
        return "list";
    }
}
