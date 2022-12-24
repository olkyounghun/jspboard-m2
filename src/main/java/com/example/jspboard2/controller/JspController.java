package com.example.jspboard2.controller;
import com.example.jspboard2.domain.Board;
import com.example.jspboard2.service.BoardService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/")
public class JspController {

    @Autowired
    private BoardService boardservice;

    @GetMapping("/home")
    public String main(){
        return "home";
    }

    @RequestMapping(value = "/searchworld", method = RequestMethod.POST)
    public String start(@Param("homesearch") String search){

        /** Mapper에서 검색된 내용에 따라 이동하는 경로를 전해주는 것을 만드는게 좋을거 같다. */
        /** search 를 통해 받은 내용을 'search' 라는 테이블을 통해 해당 경로일경우 출력되는 페이지를 돌려주는것으로
         * 활용이 가능할꺼 같다.
         */
        String result;
        result = boardservice.start(search);

        return result;
    }

    @GetMapping("/list")
    public ModelAndView getBoardList(){

        ModelAndView mv = new ModelAndView();
        List<Board> list;
        list = boardservice.getBoardList();
        mv.addObject("list", list);
        mv.setViewName("list");
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
