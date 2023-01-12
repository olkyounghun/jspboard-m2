package com.example.jspboard2.controller;

import com.example.jspboard2.domain.Member;
import com.example.jspboard2.service.MemberService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

@Controller
public class MemberController {

    @Resource
    private MemberService memberService;

    @RequestMapping(value = "/signup", method = {RequestMethod.GET})
    public String movesignup(){


        return "signup";
    }


    @RequestMapping(value = "/signup", method = {RequestMethod.POST})
    public String getMembership(@Param("userMember") String userMember,
                                @Param("userPw") String userPw,
                                @Param("userName") String userName,
                                @Param("userGender") String userGender,
                                @Param("userEmail1") String userEmail1,
                                @Param("userEmail2") String userEmail2,
                                @Param("emailChk") String emailChk) {

        if(userMember == null || userPw == null || userName == null || userGender == null || userEmail1 == null || userEmail2 == null || emailChk == null){
            return "signup";
        }

        String userEmailComplet = userEmail1 + "@" + userEmail2;
        memberService.getMembership(userMember,userPw,userName,userGender,userEmailComplet,emailChk);

        return "login";
    }

    @GetMapping("/login")
    public  String goLogin() {return "login";}

    @PostMapping("/login")
    public String insideLogin(@Param("loignID") String loginID,
                              @Param("loginPw") String loginPw){



        return "";
    }

}
