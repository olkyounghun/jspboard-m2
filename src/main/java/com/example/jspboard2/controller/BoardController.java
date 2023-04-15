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

        HttpSession session = request.getSession();
        String loginId = String.valueOf(session.getAttribute("loginId"));
        String loginPw = String.valueOf(session.getAttribute("loginPw"));
        Member loginMember = memberService.checkLogin(loginId,loginPw);

        ModelAndView mv = new ModelAndView();
        if (session == null) {
            RedirectView redirectView = new RedirectView("/login");
            redirectView.setExposeModelAttributes(false);
            mv.setViewName("login");
            return mv;
        }
        mv.addObject("id_member",loginMember.getId_member());
        mv.setViewName("search");

        return mv;
    }

    @RequestMapping(value="/boardlist", method={RequestMethod.GET,RequestMethod.POST})
    public ModelAndView getBoardList(@Valid @ModelAttribute("mv") Member member,
                                     BindingResult bindingResult,
                                     HttpServletRequest request,
                                     @RequestParam(value = "page", required = false,defaultValue = "1") int page){

        ModelAndView mv = new ModelAndView();
        HttpSession session = request.getSession();

        Paging paging = new Paging();
        int count = boardService.getAllCount();
        paging.setPage(page);
        paging.setTotalCount(count);
        int beginpage = paging.getBeginPage();
        int endpage = paging.getEndPage();

        String loginId = String.valueOf(session.getAttribute("loginId"));
        String loginPw = String.valueOf(session.getAttribute("loginPw"));
        Member loginMember = memberService.checkLogin(loginId,loginPw);

        List<Board> list;
        list = boardService.getBoardList(beginpage,endpage,page);

        mv.addObject("id_member", loginMember.getId_member());
        mv.addObject("list", list);
        mv.addObject("paging", paging);
        mv.setViewName("boardlist");

        return mv;
    }

    @RequestMapping(value = "/searchlist",method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView getSearchResult(@Valid @ModelAttribute("mv") Member member,
                                        BindingResult bindingResult,
                                        HttpServletRequest request,
                                        @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                        @RequestParam("startDate") String startDate,
                                        @RequestParam("endDate") String endDate,
                                        @RequestParam("searchType") String searchType,
                                        @RequestParam("searchName") String searchName){

        ModelAndView mv = new ModelAndView();
        HttpSession session = request.getSession();

        Paging paging = new Paging();
        int count = boardService.getAllCount();
        paging.setPage(page);
        paging.setTotalCount(count);
        int beginpage = paging.getBeginPage();
        int endpage = paging.getEndPage();

        String loginId = String.valueOf(session.getAttribute("loginId"));
        String loginPw = String.valueOf(session.getAttribute("loginPw"));
        Member loginMember = memberService.checkLogin(loginId,loginPw);

        List<Board> list;
        list = boardService.getSearchResult(searchType, startDate, endDate, searchName);

        mv.addObject("id_member", loginMember.getId_member());
        mv.addObject("list", list);
        mv.addObject("paging", paging);
        mv.setViewName("boardlist");

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

    @GetMapping("/boardposting")
    public String movePosting(){

        return "boardposting";
    }
    @PostMapping("/postingAction")
    public ModelAndView boardPosting(@Valid @ModelAttribute("mv") Member member,
                                     @Param("typeBoard") String typeBoard,
                                     @Param("titleBoard") String titleBoard,
                                     @Param("contentBoard") String contentBoard,
                                     BindingResult bindingResult,
                                     HttpSession session){

        ModelAndView mv = new ModelAndView();
        if (bindingResult.hasErrors()) {
            mv.setViewName("login");
            return mv;
        }

        String loginId = String.valueOf(session.getAttribute("loginId"));
        String loginPw = String.valueOf(session.getAttribute("loginPw"));
        Member postMember = memberService.checkLogin(loginId,loginPw);
        String userBoard = postMember.getUser_member();
        Long idMember = postMember.getId_member();
        List<Board> list;
        list = boardService.postingUpload(typeBoard,titleBoard,contentBoard,userBoard,idMember);
        int newNumber = boardService.getNewBoardId();
        ;
        mv.addObject("list", list);
        mv.setViewName("redirect:/boarddetail/" + newNumber);

        return mv;
    }

    @GetMapping("/boardmodify/{id_board}")
    public ModelAndView boardModify(@PathVariable("id_board") Integer id_board,
                                    @Valid @ModelAttribute Member member,
                                    HttpServletRequest request,
                                    BindingResult bindingResult){

        ModelAndView mv = new ModelAndView();
        HttpSession session = request.getSession();
        String loginId = String.valueOf(session.getAttribute("loginId"));
        String loginPw = String.valueOf(session.getAttribute("loginPw"));
        if ( loginId == null && loginPw == null) {
            bindingResult.reject("loginFail", "로그인 정보가 없습니다.");
            mv.setViewName("redirect:/login");
            return mv;
        }
        Member loginMember = memberService.checkLogin(loginId,loginPw);
        List<Board> list;
        list = boardService.getDetailBoard(id_board);
        Board boardInfo = boardService.getMatchPoint(id_board);

        if(loginMember.getId_member() != boardInfo.getId_member()){
            bindingResult.reject("loginFail", "회원정보와 로그인정보가 일치하지않습니다.");
            mv.setViewName("redirect:/login");
            return mv;
        }else{
            mv.addObject("list", list);
            mv.setViewName("boardmodify");
            return mv;
        }
    }

    @RequestMapping(value = "/modifyAction", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView boardModifyAction(@RequestParam("idBoard") Integer idBoard,
                                          @RequestParam("typeBoard") String typeBoard,
                                          @RequestParam("titleBoard") String titleBoard,
                                          @RequestParam("contentBoard") String contentBoard,
                                          @Valid @ModelAttribute Member member,
                                          BindingResult bindingResult,
                                          HttpServletRequest request){

        ModelAndView mv = new ModelAndView();
        HttpSession session = request.getSession();
        String loginId = String.valueOf(session.getAttribute("loginId"));
        String loginPw = String.valueOf(session.getAttribute("loginPw"));
        if ( loginId == null && loginPw == null) {
            bindingResult.reject("loginFail", "로그인 정보가 없습니다.");
            mv.setViewName("redirect:/login");
            return mv;
        }
        Member loginMember = memberService.checkLogin(loginId,loginPw);
        List<Board> list;
        Board boardInfo = boardService.getMatchPoint(idBoard);

        if(loginMember.getId_member() != boardInfo.getId_member()){
            mv.addObject("error","loginFail");
            mv.addObject("errorMessage", "회원정보와 로그인정보가 일치하지않습니다.");
            mv.addObject("errorMove","redirect:/login");
            mv.setViewName("error");
        }else{
            boardService.postModifyBoard(typeBoard,titleBoard,contentBoard,idBoard);
            list = boardService.getDetailBoard(idBoard);
            mv.addObject("list",list);
            mv.setViewName("boarddetail");
        }
        return mv;
    }

    @GetMapping("/boarddetail/{id_board}")
    public ModelAndView boardDetail(@PathVariable("id_board") Integer id_board,
                                    @Valid @ModelAttribute Member member,
                                    BindingResult bindingResult,
                                    HttpServletRequest request){

        ModelAndView mv = new ModelAndView();
        if (bindingResult.hasErrors()) {
            mv.setViewName("login");
            return mv;
        }
        HttpSession session = request.getSession();
        String loginId = String.valueOf(session.getAttribute("loginId"));
        String loginPw = String.valueOf(session.getAttribute("loginPw"));
        Member loginMember = memberService.checkLogin(loginId,loginPw);

        List<Board> list;
        boardService.viewUpPoint(id_board);
        list = boardService.getDetailBoard(id_board);
        mv.addObject("id_member",loginMember.getId_member());
        mv.addObject("list", list);
        mv.setViewName("boarddetail");

        return mv;
    }

    @GetMapping("/boarddelete/{id_board}")
    public ModelAndView boardDelete(@PathVariable("id_board") Integer id_board,
                                    @Valid @ModelAttribute Member member,
                                    HttpServletRequest request,
                                    BindingResult bindingResult){

        ModelAndView mv = new ModelAndView();
        HttpSession session = request.getSession();
        String loginId = String.valueOf(session.getAttribute("loginId"));
        String loginPw = String.valueOf(session.getAttribute("loginPw"));
        if ( loginId == null && loginPw == null) {
            bindingResult.reject("loginFail", "로그인 정보가 없습니다.");
            mv.setViewName("redirect:/login");
            return mv;
        }
        Member loginMember = memberService.checkLogin(loginId,loginPw);
        Board boardInfo = boardService.getMatchPoint(id_board);

        if(loginMember.getId_member() != boardInfo.getId_member()){
            mv.addObject("error","login");
            mv.addObject("errorMessage","해당 글의 삭제권한이 없습니다.");
            mv.addObject("errorMove","boardlist");
            mv.setViewName("error");
        }else{
            boardService.boardDeleteAction(id_board);
            mv.setViewName("redirect:/boardlist");
        }
        return mv;
    }

}
