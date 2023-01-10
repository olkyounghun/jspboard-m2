package com.example.jspboard2.service;

import com.example.jspboard2.domain.Member;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.servlet.ModelAndView;

public interface MemberService {

    Member getMembership(@Param("userMember") String userMember,
                               @Param("userPw") String userPw,
                               @Param("userName") String userName,
                               @Param("userGender") Integer userGender,
                               @Param("userEmailComplet") String userEmailComplet,
                               @Param("emailChk") Integer emailChk);

}
