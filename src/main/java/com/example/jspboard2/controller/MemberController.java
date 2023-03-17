package com.example.jspboard2.controller;

import com.example.jspboard2.domain.Member;
import com.example.jspboard2.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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


    @RequestMapping(value = "/signup", method = {RequestMethod.GET})
    public String movesignup(){


        return "signup";
    }


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
        mv.setViewName("home");
        return mv;
    }

    @RequestMapping(value = "/memberdetail/{id_member}", method = {RequestMethod.GET, RequestMethod.POST})
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

    @GetMapping("/membermodify")
    public ModelAndView movePosting(@Valid @ModelAttribute Member member,
                                    BindingResult bindingResult,
                                    HttpServletRequest request,
                                    @Param("id_member") int idMember){

        ModelAndView mv = new ModelAndView();
        if (bindingResult.hasErrors()) {
            mv.setViewName("login");
            return mv;
        }

        String loginMember = memberService.checkLogin(member.getUser_member(), member.getPassword_member());
        HttpSession session = request.getSession();
        if(member.getRating_member() != 1){
            mv.setViewName("login");
            return mv;
        }
        session.setAttribute("userName", loginMember);
        List<Member> list;
        list = memberService.getMember(idMember);
        mv.addObject("list",list);
        mv.setViewName("membermodify?id_member="+idMember);

        return mv;
    }

    @GetMapping ("membermodify/deleteMember")
    public ModelAndView deleteMember(@Valid @ModelAttribute Member member,
                               BindingResult bindingResult,
                               HttpServletRequest request,
                               @Param("id_member") int idMember){

        ModelAndView mv = new ModelAndView();
        if (bindingResult.hasErrors()) {
            mv.setViewName("login");
            return mv;
        }
        String deleteDone;
        String loginMember = memberService.checkLogin(member.getUser_member(), member.getPassword_member());
        HttpSession session = request.getSession();
        if(member.getRating_member() != 1 || member.getId_member() != idMember){ // 둘다 아니라면 로그인
            mv.setViewName("login");
            return mv;
        }else if(member.getRating_member() == 1){ // 관리자가 개인 멤버 삭제시
            deleteDone = memberService.deleteMember(idMember);
            mv.setViewName("manager");
        }else{ // 멤버 본인일때 본인 회원정보 삭제시
            deleteDone = memberService.deleteMember(idMember);
            mv.setViewName("login");
        }
        session.setAttribute("userName", loginMember);

        return mv;
    }


//    @GetMapping("/add")
//    public String addForm(@ModelAttribute("member") Member member) {
//        return "/members/addMemberForm";
//    }
//
//    @PostMapping("/add")
//    public String save(@Valid @ModelAttribute Member member, BindingResult bindingResult) { // BindingResult ??
//        if (bindingResult.hasErrors()) { // hasErroes ??
//            return "/members/addMemberForm";
//        }
//
//        memberRepository.save(member);
//        return "redirect:/";
//    }
/** 세션 어노테이션을 확인해서 다시 시도해보자 */



}
