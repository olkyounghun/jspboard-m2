package com.example.jspboard2.controller;

import com.example.jspboard2.domain.Member;
import com.example.jspboard2.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
        if ( loginId == null && loginPw == null) {
            bindingResult.reject("loginFail", "로그인 정보가 없습니다.");
            mv.setViewName("login");
            return mv;
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
                                 HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        if (bindingResult.hasErrors()) {
            mv.setViewName("login");
            return mv;
        }

        Member loginMember = memberService.checkLogin(loginId,loginPw);

        if (loginId == null || loginPw == null) {
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
            RedirectView redirectView = new RedirectView("/home");
            redirectView.setExposeModelAttributes(false);
            mv.setViewName("search");
        }

        return mv;
    }

    // 세션정보에 로그인 정보 삭제
    @RequestMapping(value = "/logout", method={RequestMethod.GET,RequestMethod.POST})
    public String EndLogout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "login";
    }



}