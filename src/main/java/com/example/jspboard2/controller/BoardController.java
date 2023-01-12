package com.example.jspboard2.controller;
import com.example.jspboard2.domain.Board;
import com.example.jspboard2.domain.Paging;
import com.example.jspboard2.service.BoardService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/")
public class BoardController {

    @Resource
    private BoardService boardService;

    @GetMapping("/home")
    public String main(){
        return "home";
    }

    @GetMapping("/list")
    public ModelAndView getBoardList(@Param("page") int page){


        Paging paging = new Paging();
        int count = boardService.getAllCount();
        int goPage = 1;
        if(page != 0){
            goPage = page;
        }

        paging.setPage(page);
        paging.setTotalCount(count);

        ModelAndView mv = new ModelAndView();
        List<Board> list;
        list = boardService.getBoardList(page);
        mv.addObject("list", list);
        mv.addObject("pageing", paging);
        mv.setViewName("list");
        mv.setViewName("paging");
        return mv;
    }

    @GetMapping(value = "/searchlist")
    public ModelAndView getSearchResult(@Param("startDate") String startDate,
                                        @Param("endDate") String endDate,
                                        @Param("searchType") String searchType,
                                        @Param("searchName") String searchName){

        /* 검색내용 > 서비스로 보내고 > 메퍼에서 검색해서 > 컨트롤에서 다시 보내기기*/
        ModelAndView mv = new ModelAndView();
        List<Board> list = null;
        list = boardService.getSearchResult(startDate,endDate,searchType,searchName);
        mv.addObject("list", list);
        mv.setViewName("list");

        return mv;
    }

}
