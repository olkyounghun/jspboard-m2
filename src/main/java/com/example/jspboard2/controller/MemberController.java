package com.example.jspboard2.controller;

import com.example.jspboard2.domain.Member;
import com.example.jspboard2.domain.Paging;
import com.example.jspboard2.service.MemberService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class MemberController {

    @Resource
    private MemberService memberService;

    
    // 회원가입페이지 이동
    @RequestMapping(value = "/signup", method = {RequestMethod.GET})
    public String movesignup(){
        
        return "signup";
    }


    // 회원가입 정보 입력후 완료
    @RequestMapping(value = "/signup", method = {RequestMethod.POST})
    public ModelAndView getMembership(@Param("userMember") String userMember,
                                @Param("userPw") String userPw,
                                @Param("userName") String userName,
                                @Param("userGender") String userGender,
                                @Param("userEmail1") String userEmail1,
                                @Param("userEmail2") String userEmail2,
                                @Param("emailChk") String emailChk,
                                HttpServletRequest request) {

        ModelAndView mv = new ModelAndView();
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
            session = request.getSession();
        }

        String userEmailComplet = userEmail1 + "@" + userEmail2;
        List<Member> list;
        list  = memberService.getMembership(userMember,userPw,userName,userGender,userEmailComplet,emailChk);
        Member member = new Member();
        member.setMember(userMember,userPw,userName,userEmailComplet,userGender);
        member.printValue();

        session.setAttribute("userName",userMember);
        mv.addObject("list",list);
        mv.setViewName("search");
        return mv;
    }

    @RequestMapping(value = "/memberdetail/{id_member}", method = {RequestMethod.GET})
    public ModelAndView memberInformationCheck(@PathVariable("id_member") Integer idMember,
                                     @Valid @ModelAttribute Member member,
                                     HttpServletRequest request){

        ModelAndView mv = new ModelAndView();
        HttpSession session = request.getSession();
        if (session.getAttribute("userName") == null) {
            mv.setViewName("login");
            return mv;
        }
        List<Member> list;
        list = memberService.getMember(idMember);
        mv.addObject("list", list);
        mv.setViewName("memberdetail");

        return mv;
    }

    // 개인 회원정보 확인
    @RequestMapping(value = "/membermodify/{id_member}", method = {RequestMethod.POST})
    public ModelAndView memberModify(@RequestParam(value = "id_member", required = false) Integer idMember,
                                     @RequestParam(value = "emailMember", required = false) String emailMember,
                                     @RequestParam(value = "nameMember", required = false) String nameMemeber,
                                     @Valid @ModelAttribute Member member,
                                     HttpServletRequest request){

        ModelAndView mv = new ModelAndView();
        HttpSession session = request.getSession();
        if (session.getAttribute("userName") == null) {
            mv.setViewName("login");
            return mv;
        }

        List<Member> list;
        list = memberService.modifyMemberDetail(idMember,emailMember,nameMemeber);

        mv.addObject("list",list);
        mv.setViewName("memberdetail");

        return mv;
    }

    @RequestMapping(value = "/memberdelete/{id_member}", method = {RequestMethod.GET})
        public String memberInfoDelete(@PathVariable("id_member") Integer idMember,
                                       @Valid @ModelAttribute Member member,
                                       HttpServletRequest request){

        HttpSession session = request.getSession();
        if (session.getAttribute("userName") == null) {
            return "login";
        }

        String deleteDone;
        deleteDone = memberService.deleteMember(idMember);
        session.invalidate();  // 삭제된 개인 회원세션 로그아웃 하기
        
        return "login";
    }

    // 매니저 등급 확인이후 매니저게시판으로 이동 및 회원게시판 읽어오기
    @RequestMapping(value="/manager", method={RequestMethod.GET,RequestMethod.POST})
    public ModelAndView managerLogin(@Valid @ModelAttribute Member member, BindingResult bindingResult, HttpServletRequest request,
                                     @RequestParam(value = "page", required = false,defaultValue = "1") int page){

        ModelAndView mv = new ModelAndView();

        if (bindingResult.hasErrors()) {
            RedirectView redirectView = new RedirectView("/login");
            redirectView.setExposeModelAttributes(false);
            mv.setViewName("login");
            return mv;
        }

        HttpSession session = request.getSession();
        session.getAttribute("userName");
        String userName = (String)session.getAttribute("userName");
        Member userInfo;
        userInfo = memberService.getuserName(userName);

        if(userInfo.getRating_member() == 2){
            RedirectView redirectView = new RedirectView("/manager");
            redirectView.setExposeModelAttributes(false);
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
            RedirectView redirectView = new RedirectView("/list");
            redirectView.setExposeModelAttributes(false);
            mv.setViewName("boardlist");
        }

        return mv;
    }

    // 매니저게시판 특정 회원정보 확인
    @GetMapping("/managerinfo/{id_member}")
    public ModelAndView MemberDetail(@PathVariable("id_member") Integer id_member,
                                     @Valid @ModelAttribute Member member,
                                     BindingResult bindingResult,
                                     HttpServletRequest request){

        ModelAndView mv = new ModelAndView();

        if (bindingResult.hasErrors()) {
            RedirectView redirectView = new RedirectView("/login");
            redirectView.setExposeModelAttributes(false);
            mv.setViewName("login");
            return mv;
        }

        HttpSession session = request.getSession();
        session.getAttribute("userName");

        List<Member> list;
        list = memberService.getMember(id_member);

        mv.addObject("list",list);
        RedirectView redirectView = new RedirectView("/memberinfo");
        redirectView.setExposeModelAttributes(false);
        mv.setViewName("managerinfo");

        return mv;
    }

    // 매니저게시판 특정회원정보 삭제
    @GetMapping ("managerdelete/{id_member}")
    public ModelAndView deleteMember(@PathVariable("id_member") Integer idMember,
                                     @Valid @ModelAttribute Member member,
                                     BindingResult bindingResult,
                                     HttpServletRequest request){

        ModelAndView mv = new ModelAndView();
        if (bindingResult.hasErrors()) {
            mv.setViewName("login");
            return mv;
        }
        String deleteDone;
        HttpSession session = request.getSession();
        Member sessionMember = (Member)session.getAttribute("userName");
        Member loginMember = memberService.checkLogin(sessionMember.getUser_member(), sessionMember.getPassword_member());

        if(loginMember.getRating_member() != 1 ){ // 둘다 아니라면 로그인
            mv.setViewName("login");
            return mv;
        }else if(loginMember.getRating_member() == 1){ // 관리자가 개인 멤버 삭제시
            deleteDone = memberService.deleteMember(idMember);
            mv.setViewName("manager");
        }
        //session.setAttribute("userName", loginMember);

        return mv;
    }
    
}
