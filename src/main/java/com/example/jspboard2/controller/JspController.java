package com.example.jspboard2.controller;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class JspController {

    @GetMapping("/Home")
    public String main(){
        return "home";
    }

    @GetMapping("/search")
    public String search(){
        String search = "";
        switch (search){
            case "List" : return "list";
            case "list" : return "list";
            case "목록" : return "list";
            case "리스트" : return "list";
            case "로그인" : return "login";
            case "login" : return "what";
            case "Login" : return "what";
            default : return "search";
        }
    }
}
