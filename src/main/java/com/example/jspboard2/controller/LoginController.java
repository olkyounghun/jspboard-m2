package com.example.jspboard2.controller;

import com.example.jspboard2.domain.LoginForm;
import com.example.jspboard2.domain.LoginService;
import com.example.jspboard2.domain.Member;
import com.example.jspboard2.service.MemberService;
import com.example.jspboard2.service.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    private final MemberService memberService;

    @GetMapping("/login")
    public ModelAndView loginForm(HttpServletRequest request
                            , @ModelAttribute("loginMember") Member loginMember) {

        ModelAndView mv = new ModelAndView();
        HttpSession session = request.getSession();
        String logindata = (String)session.getAttribute("loginMember");
        mv.addObject("loginMember",logindata);
        mv.setViewName("login");

        return mv;
    }
//
//    @PostMapping("/login")
//    public String login(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult) { // valid 와 modelAttribute ??
//        if (bindingResult.hasErrors()) {
//            return "login";
//        }
//
//        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
//
//        if(loginMember == null) {
//            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");  // binding ??
//            return "login";
//        }
//
//        // 로그인 성공 처리 TODO
//
//        return "redirect:/";
//    }

    // //
    @PostMapping("/login")
    public ModelAndView loginV3(@Valid @Param("loginId") String loginId,
                                @Param("loginPw") String loginPw,
                                BindingResult bindingResult,
                                HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        if (bindingResult.hasErrors()) {
            mv.setViewName("login");
            return mv;
        }

        String loginMember = memberService.checkLogin(loginId,loginPw);

        System.out.println("login? " + loginMember);

        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            mv.setViewName("login");
            return mv;
        }

        // 로그인 성공 처리
        //세션이 있으면 있는 세션 반환, 없으면 신규 세션 생성
        HttpSession session = request.getSession(); //세션에 로그인 회원 정보 보관
        session.setAttribute("loginMember", loginMember);
        mv.addObject("loginMember",loginMember);
        return mv;
    }

    @PostMapping("/logout")
    public String logoutV3(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

    @GetMapping("/manager")
    public String managerLogin(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletRequest request){

        String moveHere;

        if (bindingResult.hasErrors()) {
            return "login";
        }

        Member loginMember = loginService.login(form.getLoginId(),form.getPassword());
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        if(loginMember.getRating_member() == 1){
            moveHere = "manager";
        }else{
            moveHere = "login";
        }

        return moveHere;
    }

}