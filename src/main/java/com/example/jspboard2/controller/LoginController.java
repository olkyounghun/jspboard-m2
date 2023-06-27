package com.example.jspboard2.controller;

import com.example.jspboard2.domain.Member;
import com.example.jspboard2.domain.Paging;
import com.example.jspboard2.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;

    // 로그인 
    @GetMapping(value={"/login", "/"})
    public ModelAndView loginForm(@Valid Member member,
                                  BindingResult bindingResult,
                                  HttpServletRequest request) {

        ModelAndView mv = new ModelAndView();
        HttpSession session = request.getSession();
        String loginId = String.valueOf(session.getAttribute("loginId"));
        String loginPw = String.valueOf(session.getAttribute("loginPw"));
        Member loginMember = memberService.checkLogin(loginId,loginPw);
        if (loginId == "null" && loginPw == "null") {
            mv.addObject("error","loginFail");
            mv.addObject("errorMessage", "로그인정보가 없습니다.");
            mv.addObject("errorMove","/login");
            mv.setViewName("error");
        }else{
            mv.addObject("id_member",loginMember.getId_member());
        }
        mv.setViewName("login");

        return mv;
    }
    
    // 로그인시 세션에 정보 저장
    @PostMapping("/login")
    public ModelAndView StartLogin( @Param("loginId") String loginId,
                                    @Param("loginPw") String loginPw,
                                    @Valid Member member,
                                    BindingResult bindingResult,
                                    HttpServletRequest request,
                                    @RequestParam(value = "page", required = false,defaultValue = "1") int page) {
        ModelAndView mv = new ModelAndView();
        Member loginMember = memberService.checkLogin(loginId,loginPw);

        if (loginMember == null) {
            mv.addObject("error","login");
            mv.addObject("errorMessage","로그인 정보를 전부 입력해주세요.");
            mv.addObject("errorMove","login");
            mv.setViewName("error");
        }else{
            // 로그인 성공 처리
            //세션이 있으면 있는 세션 반환, 없으면 신규 세션 생성
            HttpSession session = request.getSession(); //세션에 로그인 회원 정보 보관
            session.setAttribute("loginId", loginId);
            session.setAttribute("loginPw", loginPw);
            mv.addObject("id_member",loginMember.getId_member());

            if(loginMember.getRating_member() == 1){
                Paging paging = new Paging();
                int count = memberService.getAllManager();

                paging.setPage(page);
                paging.setTotalCount(count);
                int beginpage = paging.getBeginPage();
                int endpage = paging.getEndPage();

                List<Member> list;
                list = memberService.getManagerMember(beginpage,endpage,page);
                mv.addObject("list",list);
                mv.addObject("paging", paging);

                RedirectView redirectView = new RedirectView("/home");
                redirectView.setExposeModelAttributes(false);
                mv.setViewName("manager");
            }else{
                RedirectView redirectView = new RedirectView("/home");
                redirectView.setExposeModelAttributes(false);
                mv.setViewName("search");
            }
        }

        return mv;
    }

    // 세션정보에 로그인 정보 삭제
    @RequestMapping(value = "/logout", method={RequestMethod.GET,RequestMethod.POST})
    public String EndLogout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        session.removeAttribute("loginId");
        session.removeAttribute("loginPw");
        session.invalidate();

        return "login";
    }



}