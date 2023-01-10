package com.example.jspboard2.mapper;

import com.example.jspboard2.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.servlet.ModelAndView;

@Mapper
public interface MemberMapper {

    Member getMembership(@Param("userMember") String userMember,
                               @Param("userPw") String userPw,
                               @Param("userName") String userName,
                               @Param("userGender") Integer userGender,
                               @Param("userEmailComplet") String userEmailComplet,
                               @Param("emailChk") Integer emailChk);




}
