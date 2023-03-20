package com.example.jspboard2.mapper;

import com.example.jspboard2.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberMapper {

    List<Member> getMembership(@Param("userMember") String userMember,
                               @Param("userPw") String userPw,
                               @Param("userName") String userName,
                               @Param("userGender") String userGender,
                               @Param("userEmailComplet") String userEmailComplet,
                               @Param("emailChk") String emailChk);

    String checkLogin(@Param("loginId") String loginID, @Param("loginPw") String loginPw);

    List<Member> modifyMemberDetail(@Param("id_member") int idMember,
                                    @Param("nameMember") String nameMember,
                                    @Param("emailMember") String emailMember);

    String deleteMember(@Param("idMember") int idMember);

    List<Member> getMember(@Param("idMember") int idMember);

    int getAllManager();

    List<Member> getManagerMember(@Param("beginpage")int beginpage,
                                  @Param("endpage") int endpage,
                                  @Param("page") int page);

    Member getuserName(@Param("userName") String userName);

    List<Member> getMemberInfo(@Param("id_member") int id_member);

}
