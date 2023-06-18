package com.example.jspboard2.service;

import com.example.jspboard2.domain.Member;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MemberService {

    List<Member> getMembership(@Param("userMember") String userMember,
                               @Param("userPw") String userPw,
                               @Param("userName") String userName,
                               @Param("userEmail") String userEmail,
                               @Param("emailChk") String emailChk,
                               @Param("userGender") String userGender);

    Member checkLogin(@Param("loginId") String loginID, @Param("loginPw") String loginPw);

    List<Member> modifyMemberDetail(@Param("idMember") int idMember,
                                    @Param("emailMember") String emailMember,
                                    @Param("nameMember") String nameMember);
    String deleteMember(@Param("idMember") int idMember);
    List<Member> getMember(@Param("idMember") int idMember);

    List<Member> getPrevMember(@Param("id_member") int id_member);

    List<Member> getNextMember(@Param("id_member") int id_member);

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

    List<Member> getMmodifyAction(@Param("idMember") int idMember,
                                  @Param("userMember") String userMember,
                                  @Param("regdateMember") String regdateMember,
                                  @Param("nameMember") String nameMember,
                                  @Param("genderMember") String genderMember,
                                  @Param("emailMember") String emailMember);

    List<Member> getManagerMember(@Param("beginpage")int beginpage,
                                  @Param("endpage") int endpage,
                                  @Param("page") int page);

    List<Member> getManagerPageMember(@Param("pagelist") int pagelist);


}