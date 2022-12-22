package com.example.jspboard2.controller;
import com.example.jspboard2.domain.Board;
import com.example.jspboard2.service.BoardService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/")
public class JspController {

    @Autowired
    private BoardService boardservice;

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

    @GetMapping("/list")
    public ModelAndView getBoardList(){

        ModelAndView mv = new ModelAndView();
        List<Board> list = null;
        list = boardservice.getBoardList();
        mv.addObject("list", list);
        mv.setViewName("list");
        return mv;
    }

    @GetMapping(value = "/list")
    public ModelAndView getSearchResult(@Param("startDate") String startDate,
                                        @Param("endDate") String endDate,
                                        @Param("searchType") String searchType,
                                        @Param("searchName") String searchName){

        /* 검색내용 > 서비스로 보내고 > 메퍼에서 검색해서 > 컨트롤에서 다시 보내기기*/
        ModelAndView mv = new ModelAndView();
        List<Board> list = null;
        list = boardservice.getSearchResult(startDate,endDate,searchType,searchName);
        mv.addObject("list", list);
        mv.setViewName("list");

        return mv;
    }

    @GetMapping("/login")
    public  String login() {
        return "login";
    }


}
