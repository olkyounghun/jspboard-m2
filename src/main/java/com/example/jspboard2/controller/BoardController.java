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
                                     HttpServletRequest request){

        ModelAndView mv = new ModelAndView();
        HttpSession session = request.getSession();

        Paging paging = new Paging();
        int count = boardService.getAllCount();
        paging.setTotalCount(count);

        String loginId = String.valueOf(session.getAttribute("loginId"));
        String loginPw = String.valueOf(session.getAttribute("loginPw"));
        Member loginMember = memberService.checkLogin(loginId,loginPw);

        List<Board> list;
        list = boardService.getBoardList();

        mv.addObject("id_member", loginMember.getId_member());
        mv.addObject("list", list);
        mv.addObject("paging", paging);
        mv.setViewName("boardlist");

        return mv;
    }

    @RequestMapping(value="/boardlist/{page}", method={RequestMethod.GET,RequestMethod.POST})
    public ModelAndView getPageList(@Valid @ModelAttribute("mv") Member member,
                                     BindingResult bindingResult,
                                     HttpServletRequest request,
                                     @PathVariable(value = "page", required = false) Integer page){

        ModelAndView mv = new ModelAndView();
        HttpSession session = request.getSession();

        if(page == null || page <= 0){
            page = 1;
        }
        Paging paging = new Paging();
        int count = boardService.getAllCount();
        paging.setPage(page);
        paging.setTotalCount(count);
        int pagelist = (page-1)*10;

        String loginId = String.valueOf(session.getAttribute("loginId"));
        String loginPw = String.valueOf(session.getAttribute("loginPw"));
        Member loginMember = memberService.checkLogin(loginId,loginPw);

        List<Board> list;
        list = boardService.getPageList(pagelist);

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
                                        @RequestParam(value = "startDate", required = false) String startDate,
                                        @RequestParam(value = "endDate", required = false) String endDate,
                                        @RequestParam(value = "searchType", required = false) String searchType,
                                        @RequestParam(value = "searchName", required = false) String searchName){

        ModelAndView mv = new ModelAndView();
        HttpSession session = request.getSession();

        Paging paging = new Paging();
        int count = boardService.getSearchAllCount(searchType, startDate, endDate, searchName);
        paging.setPage(page);
        paging.setTotalCount(count);

        String loginId = String.valueOf(session.getAttribute("loginId"));
        String loginPw = String.valueOf(session.getAttribute("loginPw"));
        Member loginMember = memberService.checkLogin(loginId,loginPw);

        List<Board> list;
        list = boardService.getSearchResult(searchType, startDate, endDate, searchName);

        mv.addObject("id_member", loginMember.getId_member());
        mv.addObject("list", list);
        mv.addObject("paging", paging);
        mv.addObject("startDate",startDate);
        mv.addObject("endDate",endDate);
        mv.addObject("searchType",searchType);
        mv.addObject("searchName",searchName);
        mv.setViewName("searchlist");

        return mv;

    }


    @RequestMapping(value = "/searchlist/{page}",method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView getSearchPageResult(@Valid @ModelAttribute("mv") Member member,
                                        BindingResult bindingResult,
                                        HttpServletRequest request,
                                        @PathVariable(value = "page", required = false) Integer page,
                                        @RequestParam(value = "startDate", required = false) String startDate,
                                        @RequestParam(value = "endDate", required = false) String endDate,
                                        @RequestParam(value = "searchType", required = false) String searchType,
                                        @RequestParam(value = "searchName", required = false) String searchName){

        ModelAndView mv = new ModelAndView();
        HttpSession session = request.getSession();

        if(page == null || page <= 0){
            page = 1;
        }

        if(searchType == null){
            searchType = "ALL";
        }

        Paging paging = new Paging();
        int count = boardService.getSearchAllCount(searchType, startDate, endDate, searchName);
        paging.setPage(page);
        paging.setTotalCount(count);
        int pagelist = (page-1)*10;

        String loginId = String.valueOf(session.getAttribute("loginId"));
        String loginPw = String.valueOf(session.getAttribute("loginPw"));
        Member loginMember = memberService.checkLogin(loginId,loginPw);

        List<Board> list;
        list = boardService.getSearchPageResult(searchType, startDate, endDate, searchName,pagelist);

        mv.addObject("id_member", loginMember.getId_member());
        mv.addObject("list", list);
        mv.addObject("paging", paging);
        mv.addObject("startDate",startDate);
        mv.addObject("endDate",endDate);
        mv.addObject("searchType",searchType);
        mv.addObject("searchName",searchName);

        mv.setViewName("searchlist");

        return mv;
    }

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

        String loginId = String.valueOf(session.getAttribute("loginId"));
        String loginPw = String.valueOf(session.getAttribute("loginPw"));
        if ( loginId == null && loginPw == null) {
            mv.addObject("error","loginFail");
            mv.addObject("errorMessage", "로그인정보가 없습니다.");
            mv.addObject("errorMove","/login");
            mv.setViewName("error");
        }

        Member postMember = memberService.checkLogin(loginId,loginPw);
        String userBoard = postMember.getUser_member();
        Long idMember = postMember.getId_member();
        List<Board> list;
        list = boardService.postingUpload(typeBoard,titleBoard,contentBoard,userBoard,idMember);
        int newNumber = boardService.getNewBoardId();
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
            mv.addObject("error","loginFail");
            mv.addObject("errorMessage", "로그인정보가 없습니다.");
            mv.addObject("errorMove","/login");
            mv.setViewName("error");
            return mv;
        }
        Member loginMember = memberService.checkLogin(loginId,loginPw);
        List<Board> list;
        list = boardService.getDetailBoard(id_board);
        Board boardInfo = boardService.getMatchPoint(id_board);

        if(loginMember.getRating_member() == 1){ // 관리자계정이라도 수정이 가능하도록
            mv.addObject("id_member", loginMember.getId_member());
            mv.addObject("list", list);
            mv.setViewName("boardmodify");
        }else if(loginMember.getId_member() != boardInfo.getId_member()) {
            mv.addObject("error", "loginFail");
            mv.addObject("errorMessage", "회원정보와 로그인정보가 일치하지않습니다.");
            mv.addObject("errorMove", "/boardlist");
            mv.setViewName("error");
        }else{
            mv.addObject("id_member", loginMember.getId_member());
            mv.addObject("list", list);
            mv.setViewName("boardmodify");
        }
        return mv;
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
            mv.addObject("error","loginFail");
            mv.addObject("errorMessage", "로그인정보가 없습니다.");
            mv.addObject("errorMove","/login");
            mv.setViewName("error");
        }
        Member loginMember = memberService.checkLogin(loginId,loginPw);
        List<Board> list;
        List<Board> prev;
        List<Board> next;
        Board boardInfo = boardService.getMatchPoint(idBoard);

        if(loginMember.getRating_member() == 1){ // 관리자계정이라도 수정이 가능하도록
            boardService.postModifyBoard(typeBoard,titleBoard,contentBoard,idBoard);
            boardService.viewUpPoint(idBoard);
            list = boardService.getDetailBoard(idBoard);
            next = boardService.getNextBoard(idBoard);
            prev = boardService.getPrevBoard(idBoard);
            if(next != null){
                mv.addObject("next", next);
            }
            if(prev != null){
                mv.addObject("prev", prev);
            }
            mv.addObject("id_member",loginMember.getId_member());
            mv.addObject("list",list);
            mv.setViewName("boarddetail");
        }else if(loginMember.getId_member() == boardInfo.getId_member()){
            boardService.postModifyBoard(typeBoard,titleBoard,contentBoard,idBoard);
            boardService.viewUpPoint(idBoard);
            list = boardService.getDetailBoard(idBoard);
            next = boardService.getNextBoard(idBoard);
            prev = boardService.getPrevBoard(idBoard);
            if(next != null){
                mv.addObject("next", next);
            }
            if(prev != null){
                mv.addObject("prev", prev);
            }
            mv.addObject("id_member",loginMember.getId_member());
            mv.addObject("list",list);
            mv.setViewName("boarddetail");
        }else{
            mv.addObject("error","loginFail");
            mv.addObject("errorMessage", "회원정보와 로그인정보가 일치하지않습니다.");
            mv.addObject("errorMove","/login");
            mv.setViewName("error");
        }
        return mv;
    }

    @GetMapping("/boarddetail/{id_board}")
    public ModelAndView boardDetail(@PathVariable("id_board") Integer id_board,
                                    @Valid @ModelAttribute Member member,
                                    BindingResult bindingResult,
                                    HttpServletRequest request){

        ModelAndView mv = new ModelAndView();
        HttpSession session = request.getSession();
        session.setAttribute("id_board",id_board);
        String loginId = String.valueOf(session.getAttribute("loginId"));
        String loginPw = String.valueOf(session.getAttribute("loginPw"));
        if ( loginId == null && loginPw == null) {
            mv.addObject("error","loginFail");
            mv.addObject("errorMessage", "로그인정보가 없습니다.");
            mv.addObject("errorMove","/login");
            mv.setViewName("error");
        }
        Member loginMember = memberService.checkLogin(loginId,loginPw);

        List<Board> list;
        List<Board> prev;
        List<Board> next;
        boardService.viewUpPoint(id_board);
        list = boardService.getDetailBoard(id_board);
        next = boardService.getNextBoard(id_board);
        prev = boardService.getPrevBoard(id_board);
        if(next != null){
            mv.addObject("next", next);
        }
        if(prev != null){
            mv.addObject("prev", prev);
        }
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
            mv.addObject("error","loginFail");
            mv.addObject("errorMessage", "로그인정보가 없습니다.");
            mv.addObject("errorMove","/login");
            mv.setViewName("error");
        }
        Member loginMember = memberService.checkLogin(loginId,loginPw);
        Board boardInfo = boardService.getMatchPoint(id_board);

        if(loginMember.getRating_member() == 1){
            boardService.boardDeleteAction(id_board);
            mv.addObject("error","게시물삭제");
            mv.addObject("errorMessage", "게시물이 올바르게 '삭제' 되었습니다.");
            mv.addObject("errorMove","/boardlist");
            mv.setViewName("error");
        }else if(loginMember.getId_member() == boardInfo.getId_member()) {
            boardService.boardDeleteAction(id_board);
            mv.addObject("error","게시물삭제");
            mv.addObject("errorMessage", "게시물이 올바르게 '삭제' 되었습니다.");
            mv.addObject("errorMove","/boardlist");
            mv.setViewName("error");
        }else{
            mv.addObject("error", "login");
            mv.addObject("errorMessage", "해당 글의 삭제권한이 없습니다.");
            mv.addObject("errorMove", "/boardlist");
            mv.setViewName("error");
        }
        return mv;
    }
    }

