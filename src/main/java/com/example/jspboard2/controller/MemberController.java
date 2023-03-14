package com.example.jspboard2.controller;

import com.example.jspboard2.domain.Member;
import com.example.jspboard2.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
        mv.setViewName("home");
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

        String loginMember = memberService.checkLogin(member.getUser_member(), member.getPassword_member());
        HttpSession session = request.getSession();
        if(member.getRating_member() != 1){
            mv.setViewName("login");
            return mv;
        }else{
            memberService.deleteMember(idMember);
            mv.setViewName("manager");
        }
        session.setAttribute("userName", loginMember);

        return mv;
    }


    /*@GetMapping("/login")
    public  String goLogin() {return "login";}

    @PostMapping("/login")
    public String insideLogin(@Param("loignID") String loginID,
                              @Param("loginPw") String loginPw){



        return "";
    }*/

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
