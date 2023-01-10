package com.example.jspboard2.controller;

import com.example.jspboard2.domain.Member;
import com.example.jspboard2.service.MemberService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MemberController {

    private MemberService memberService;

    @GetMapping("/signup")
    public String movesignup(){


        return "signup";
    }


    @RequestMapping(value = "/signupchk", method = {RequestMethod.GET,RequestMethod.POST})
    public Member getMembership(@Param("userMember") String userMember,
                                @Param("userPw") String userPw,
                                @Param("userName") String userName,
                                @Param("userGender") Integer userGender,
                                @Param("userEmail1") String userEmail1,
                                @Param("userEmail2") String userEmail2,
                                @Param("emailChk") Integer emailChk) {

        Member member = new Member();
        String userEmailComplet = userEmail1 + "@" + userEmail2;
        memberService.getMembership(userMember,userPw,userName,userGender,userEmailComplet,emailChk);

        return member;
    }

}
