package com.example.jspboard2.service;

import com.example.jspboard2.domain.Member;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MemberService {

    List<Member> getMembership(@Param("userMember") String userMember,
                               @Param("userPw") String userPw,
                               @Param("userName") String userName,
                               @Param("userGender") String userGender,
                               @Param("userEmailComplet") String userEmailComplet,
                               @Param("emailChk") String emailChk);

    String checkLogin(@Param("loginId") String loginID, @Param("loginPw") String loginPw);


    void deleteMember(@Param("id_member") int idMember);
    List<Member> getMember(@Param("idMember") int idMember);

    int getAllManager();

    List<Member> getManagerMember(@Param("beginpage")int beginpage,
                                  @Param("endpage") int endpage,
                                  @Param("page") int page);

    Member getuserName(@Param("userName") String userName);

    List<Member> getMemberInfo(@Param("id_member") int id_member);

}