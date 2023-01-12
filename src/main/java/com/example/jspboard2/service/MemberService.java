package com.example.jspboard2.service;

import com.example.jspboard2.domain.Member;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.servlet.ModelAndView;

public interface MemberService {

    void getMembership(@Param("userMember") String userMember,
                               @Param("userPw") String userPw,
                               @Param("userName") String userName,
                               @Param("userGender") String userGender,
                               @Param("userEmailComplet") String userEmailComplet,
                               @Param("emailChk") String emailChk);

    insideLogin(loginID,loginPw);

}
