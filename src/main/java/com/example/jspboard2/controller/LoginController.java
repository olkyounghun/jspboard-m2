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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;

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

    @GetMapping("/login")
    public ModelAndView loginForm(HttpServletRequest request) {

        ModelAndView mv = new ModelAndView();
        HttpSession session = request.getSession();
        String logindata = (String)session.getAttribute("userName");
        mv.addObject("userName",logindata);
        mv.setViewName("login");

        return mv;
    }
    @PostMapping("/login")
    public ModelAndView loginV3( @Param("loginId") String loginId,
                                 @Param("loginPw") String loginPw,
                                 @Valid Member member,
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
        session.setAttribute("userName", loginMember);
        mv.addObject("userName",loginMember);
        mv.setViewName("home");
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

    @RequestMapping(value="/manager", method={RequestMethod.GET,RequestMethod.POST})
    public ModelAndView managerLogin(@Valid @ModelAttribute Member member, BindingResult bindingResult, HttpServletRequest request,
                                     @RequestParam(value = "page", required = false,defaultValue = "1") int page){

        ModelAndView mv = new ModelAndView();

        if (bindingResult.hasErrors()) {
            mv.setViewName("login");
            return mv;
        }

        HttpSession session = request.getSession();
        session.getAttribute("userName");
        String userName = (String)session.getAttribute("userName");
        Member userInfo;
        userInfo = memberService.getuserName(userName);

        if(userInfo.getRating_member() == 2){
            mv.setViewName("manager");
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
        }else{
            mv.setViewName("list");
        }

        return mv;
    }

}