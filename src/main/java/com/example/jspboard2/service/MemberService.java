package com.example.jspboard2.service;

import com.example.jspboard2.domain.Member;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MemberService {

    void getMembership(@Param("userMember") String userMember,
                               @Param("userPw") String userPw,
                               @Param("userName") String userName,
                               @Param("userGender") String userGender,
                               @Param("userEmailComplet") String userEmailComplet,
                               @Param("emailChk") String emailChk);

    void insideLogin(@Param("loginId") String loginID, @Param("loginPw") String loginPw);


    void deleteMember(@Param("id_member") int idMember);
    List<Member> getMember(@Param("idMember") int idMember);

}