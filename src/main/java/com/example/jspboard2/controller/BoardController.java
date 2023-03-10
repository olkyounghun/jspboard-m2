package com.example.jspboard2.controller;

import com.example.jspboard2.domain.Board;
import com.example.jspboard2.domain.Member;
import com.example.jspboard2.domain.Paging;
import com.example.jspboard2.service.BoardService;
import com.example.jspboard2.service.MemberService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class BoardController {

    @Resource
    private BoardService boardService;

    @Resource
    private MemberService memberService;

    @GetMapping("/home")
    public ModelAndView main(@Valid @ModelAttribute Member member,
                             BindingResult bindingResult,
                             HttpServletRequest request){

        HttpSession session = request.getSession(false);
        ModelAndView mv = new ModelAndView();
        if (session == null) {
            RedirectView redirectView = new RedirectView("/login");
            redirectView.setExposeModelAttributes(false);
            mv.setViewName("login");
            return mv;
        }
        session.getAttribute("userName");
        mv.setViewName("home");

        return mv;
    }

    @RequestMapping(value="/list", method={RequestMethod.GET,RequestMethod.POST})
    public ModelAndView getBoardList(@Valid @ModelAttribute("mv") Member member,
                                     BindingResult bindingResult,
                                     HttpServletRequest request,
                                     @RequestParam(value = "page", required = false,defaultValue = "1") int page){

        ModelAndView mv = new ModelAndView();

        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("userName");
        Paging paging = new Paging();
        int count = boardService.getAllCount();

        paging.setPage(page);
        paging.setTotalCount(count);
        int beginpage = paging.getBeginPage();
        int endpage = paging.getEndPage();


        List<Board> list;
        list = boardService.getBoardList(beginpage,endpage,page);
        mv.addObject("userName",userName);
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

        /* ???????????? > ???????????? ????????? > ???????????? ???????????? > ??????????????? ?????? ????????????*/
        ModelAndView mv = new ModelAndView();
        List<Board> list;
        list = boardService.getSearchResult(startDate,endDate,searchType,searchName);
        mv.addObject("list", list);
        mv.setViewName("list");

        return mv;
    }

    // ???????????? ?????? 1.20
//    @GetMapping("/")
//    public String homeLoginV3(HttpServletRequest request, Model model) {
//
//        // ????????? ????????? home
//        HttpSession session = request.getSession(false);
//        if (session == null) {
//            return "home";
//        }
//
//        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
//        // ????????? ???????????? ????????? home
//        if (loginMember == null) {
//            return "home";
//        }
//
//        // ????????? ???????????? ??????????????? ??????
//        model.addAttribute("member", loginMember);
//        return "loginHome";
//    }

    @GetMapping("/posting")
    public String movePosting(@Valid @ModelAttribute Member member,
                              BindingResult bindingResult,
                              HttpServletRequest request){

        if (bindingResult.hasErrors()) {
            return "login";
        }

        String loginMember = memberService.checkLogin(member.getUser_member(), member.getPassword_member());
        HttpSession session = request.getSession();
        session.setAttribute("userName", loginMember);

        return "posting";
    }
    @PostMapping("/postingAction")
    public ModelAndView boardPosting(@Param("typeBoard") String typeBoard,
                                     @Param("titleBoard") String titleBoard,
                                     @Param("contentBoard") String contentBoard,
                                     BindingResult bindingResult){

        ModelAndView mv = new ModelAndView();
        if (bindingResult.hasErrors()) {
            mv.setViewName("login");
            return mv;
        }

        List<Board> list;
        list = boardService.postingUpload(typeBoard,titleBoard,contentBoard);
        int newNumber = boardService.getNewBoardId();
        mv.addObject("list", list);
        mv.setViewName("detail?id_board="+newNumber);

        return mv;
    }

    @GetMapping("/modify")
    public ModelAndView boardModify(@Param("id_board") int id_board,
                                    @Valid @ModelAttribute Member member,
                                    HttpServletRequest request,
                                    BindingResult bindingResult){

        ModelAndView mv = new ModelAndView();
        if (bindingResult.hasErrors()) {
            mv.setViewName("login");
            return mv;
        }
        String loginMember = memberService.checkLogin(member.getUser_member(),member.getPassword_member());
        HttpSession session = request.getSession();
        session.setAttribute("userName", loginMember);

        List<Board> list;
        list = boardService.getDetailBoard(id_board);
        mv.addObject("list", list);
        mv.setViewName("modify?id_board="+id_board );

        return mv;
    }

    @PostMapping("/modifyAction")
    public ModelAndView boardModifyAction(@Param("id_board") int idBoard,
                                    @Param("typeBoard") String typeBoard,
                                    @Param("titleBoard") String titleBoard,
                                    @Param("contentBoard") String contentBoard,
                                          BindingResult bindingResult){

        ModelAndView mv = new ModelAndView();
        if (bindingResult.hasErrors()) {
            mv.setViewName("login");
            return mv;
        }

        List<Board> list;
        list = boardService.postModifyBoard(idBoard,typeBoard,titleBoard,contentBoard);

        mv.addObject("list",list);
        mv.setViewName("detail?id_board="+idBoard);

        return mv;
    }

    @GetMapping("/detail/{id_board}")
    public ModelAndView boardDetail(@RequestParam(value = "id_board", required = false) int id_board,
                                    @Valid @ModelAttribute Member member,
                                    BindingResult bindingResult,
                                    HttpServletRequest request){

        ModelAndView mv = new ModelAndView();
        if (bindingResult.hasErrors()) {
            mv.setViewName("login");
            return mv;
        }
        HttpSession session = request.getSession();
        session.getAttribute("userName");

        List<Board> list;
        list = boardService.getDetailBoard(id_board);
        mv.addObject("list", list);
        mv.setViewName("detail");

        return mv;
    }


}
