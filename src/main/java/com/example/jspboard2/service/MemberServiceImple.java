package com.example.jspboard2.service;

import com.example.jspboard2.domain.Member;
import com.example.jspboard2.mapper.MemberMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImple implements MemberService {

    private final MemberMapper memberMapper;

    public MemberServiceImple(MemberMapper memberMapper){this.memberMapper = memberMapper; }

    public List<Member> getMembership(@Param("userMember") String userMember,
                                      @Param("userPw") String userPw,
                                      @Param("userName") String userName,
                                      @Param("userEmail") String userEmail,
                                      @Param("emailChk") String emailChk,
                                      @Param("userGender") String userGender){return memberMapper.getMembership(userMember,userPw,userName,userEmail,emailChk,userGender);
    }

    public Member checkLogin(@Param("loginId") String loginID, @Param("loginPw") String loginPw) {return memberMapper.checkLogin(loginID,loginPw);}

    public List<Member> modifyMemberDetail(@Param("idMember") int idMember,
                                           @Param("emailMember") String emailMember,
                                           @Param("nameMember") String nameMember){return memberMapper.modifyMemberDetail(idMember,emailMember,nameMember);}

    public String deleteMember(@Param("idMember") int idMember){return memberMapper.deleteMember(idMember);}

    public List<Member> getMember(@Param("idMember") int id_member){return memberMapper.getMember(id_member);}

    public List<Member> getPrevMember(@Param("id_member") int id_member){
        return memberMapper.getPrevMember(id_member);
    }

    public List<Member> getNextMember(@Param("id_member") int id_member){
        return memberMapper.getNextMember(id_member);
    }

    public int getAllManager(){return memberMapper.getAllManager();}

    public int getAllSearchManager(@Param("startDate") String startDate,
                                   @Param("endDate") String endDate,
                                   @Param("searchName") String searchName){return memberMapper.getAllSearchManager(startDate,endDate,searchName);}

    public List<Member> getSearchMember(@Param("startDate") String startDate,
                                  @Param("endDate") String endDate,
                                  @Param("searchName") String searchName){return memberMapper.getSearchMember(startDate,endDate,searchName);}

    public List<Member> getSearchPageMember(@Param("startDate") String startDate,
                                      @Param("endDate") String endDate,
                                      @Param("searchName") String searchName,
                                      @Param("pagelist") int pagelist){return memberMapper.getSearchPageMember(startDate,endDate,searchName,pagelist);}

    public List<Member> getMmodifyAction(@Param("idMember") int idMember,
                                         @Param("userMember") String userMember,
                                         @Param("regdateMember") String regdateMember,
                                         @Param("nameMember") String nameMember,
                                         @Param("genderMember") String genderMember,
                                         @Param("emailMember") String emailMember){
        return memberMapper.getMmodifyAction(idMember, userMember, regdateMember, nameMember,genderMember,emailMember);
    }

    public List<Member> getManagerMember(@Param("beginpage")int beginpage,
                                         @Param("endpage") int endpage,
                                         @Param("page") int page){return memberMapper.getManagerMember(beginpage,endpage,page);}

    public List<Member> getManagerPageMember(@Param("pagelist") int pagelist){return memberMapper.getManagerPageMember(pagelist);}


}
