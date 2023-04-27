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

    Member checkLogin(@Param("loginId") String loginID, @Param("loginPw") String loginPw);

    List<Member> modifyMemberDetail(@Param("idMember") int idMember,
                                    @Param("emailMember") String emailMember,
                                    @Param("nameMember") String nameMember);
    String deleteMember(@Param("idMember") int idMember);
    List<Member> getMember(@Param("idMember") int idMember);

    Member getMMember(@Param("idMember") int idMember);

    int getAllManager();

    int getAllSearchManager(@Param("startDate") String startDate,
                          @Param("endDate") String endDate,
                          @Param("searchName") String searchName);

    List<Member> getSearchMember(@Param("startDate") String startDate,
                                  @Param("endDate") String endDate,
                                  @Param("searchName") String searchName);

    List<Member> getSearchPageMember(@Param("startDate") String startDate,
                                      @Param("endDate") String endDate,
                                      @Param("searchName") String searchName,
                                      @Param("pagelist") int pagelist);

    List<Member> getManagerMember(@Param("beginpage")int beginpage,
                                  @Param("endpage") int endpage,
                                  @Param("page") int page);

    List<Member> getManagerPageMember(@Param("pagelist") int pagelist);


}