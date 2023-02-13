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

    @RequestMapping(value="/list", method={RequestMethod.GET,RequestMethod.POST})
    public ModelAndView getBoardList(@RequestParam(value = "page", required = false,defaultValue = "1") int page){


        Paging paging = new Paging();
        int count = boardService.getAllCount();

        paging.setPage(page);
        paging.setTotalCount(count);
        int beginpage = paging.getBeginPage();
        int endpage = paging.getEndPage();

        ModelAndView mv = new ModelAndView();
        List<Board> list;
        list = boardService.getBoardList(beginpage,endpage,page);
        mv.addObject("list", list);
        mv.addObject("paging", paging);
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
        List<Board> list;
        list = boardService.getSearchResult(startDate,endDate,searchType,searchName);
        mv.addObject("list", list);
        mv.setViewName("list");

        return mv;
    }

    // 로그아웃 추가 1.20
//    @GetMapping("/")
//    public String homeLoginV3(HttpServletRequest request, Model model) {
//
//        // 세션이 없으면 home
//        HttpSession session = request.getSession(false);
//        if (session == null) {
//            return "home";
//        }
//
//        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
//        // 세션에 데이터가 없으면 home
//        if (loginMember == null) {
//            return "home";
//        }
//
//        // 세션이 유지되면 로그인으로 이동
//        model.addAttribute("member", loginMember);
//        return "loginHome";
//    }

    @GetMapping("/posting")
    public String movePosting(){

        return "posting";
    }
    @PostMapping("/postingAction")
    public String boardPosting(@Param("typeBoard") String typeBoard,
                                     @Param("titleBoard") String titleBoard,
                                     @Param("contentBoard") String contentBoard){

        boardService.postingUpload(typeBoard,titleBoard,contentBoard);

        return "detail";
    }

    @GetMapping("/modify")
    public String boardModify(){

        return "modify";
    }

    @PostMapping("/modifyAction")
    public ModelAndView boardModifyAction(@Param("id_board") int idBoard,
                                    @Param("typeBoard") String typeBoard,
                                    @Param("titleBoard") String titleBoard,
                                    @Param("contentBoard") String contentBoard){

        List<Board> list;
        ModelAndView mv = new ModelAndView();
        list = boardService.postModifyBoard(idBoard,typeBoard,titleBoard,contentBoard);

        mv.addObject("list",list);
        mv.setViewName("detail");

        return mv;
    }

    @GetMapping("/detail")
    public ModelAndView boardDetail(@RequestParam(value = "id_board", required = false) int id_board){

        ModelAndView mv = new ModelAndView();
        List<Board> list;
        list = boardService.getDetailBoard(id_board);
        mv.addObject("list", list);
        mv.setViewName("detail");

        return mv;
    }


}
