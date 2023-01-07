package com.example.jspboard2.controller;

import com.example.jspboard2.domain.Member;
import com.example.jspboard2.service.MemberService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {

    private MemberService memberService;

    @GetMapping("/signup")
    public  Member getMembership(@Param("userMember") String userMember,
                          @Param("userPw") String userPw,
                          @Param("userName") String userName,
                          @Param("userGender") Integer userGender,
                          @Param("userEmail1") String userEmail1,
                          @Param("userEmail2") String userEmail2,
                          @Param("emailChk") Integer emailChk) {




        return Member;
    }

}
