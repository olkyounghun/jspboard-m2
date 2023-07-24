package com.example.jspboard2.controller;

import com.example.jspboard2.domain.Board;
import com.example.jspboard2.domain.Comment;
import com.example.jspboard2.domain.Member;
import com.example.jspboard2.service.BoardService;
import com.example.jspboard2.service.CommentService;
import com.example.jspboard2.service.MemberService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CommnetController {

    public MemberService memberService;

    public BoardService boardService;

    public CommentService commentService;

    public ModelAndView getComment(@Param("id_board") int id_board,
                                   @Param("title_comment") String title_comment,
                                   @Param("content_comment") String content_comment,
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
        Comment writeComment = commentService.getComment(title_comment,content_comment);
        List<Board> boardDetail;
        boardDetail = boardService.getDetailBoard(id_board);
        mv.addObject("list", boardDetail);
        mv.addObject("loginMember", loginMember);
        mv.addObject("writeComment",writeComment);
        return mv;
    }

    public ModelAndView modifyComment(@Param("id_board") int id_board,
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
        List<Board> boardDetail;
        boardDetail = boardService.getDetailBoard(id_board);
        mv.addObject("list", boardDetail);
        mv.addObject("loginMember", loginMember);
        return mv;
    }

    public ModelAndView deleteComment(@Param("id_board") int id_board,
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
        List<Board> boardDetail;
        boardDetail = boardService.getDetailBoard(id_board);

        mv.addObject("list", boardDetail);
        mv.addObject("loginMember", loginMember);
        return mv;
    }





}
