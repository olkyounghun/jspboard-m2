package com.example.jspboard2.mapper;

import com.example.jspboard2.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.servlet.ModelAndView;

@Mapper
public interface MemberMapper {

    void getMembership(@Param("userMember") String userMember,
                               @Param("userPw") String userPw,
                               @Param("userName") String userName,
                               @Param("userGender") String userGender,
                               @Param("userEmailComplet") String userEmailComplet,
                               @Param("emailChk") String emailChk);

    void insideLogin(@Param("loginId") String loginID, @Param("loginPw") String loginPw);


}
