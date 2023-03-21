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
                                      @Param("userGender") String userGender,
                                      @Param("userEmailComplet") String userEmailComplet,
                                      @Param("emailChk") String emailChk){return memberMapper.getMembership(userMember,userPw,userName,userGender,userEmailComplet,emailChk);
    }

    public Member checkLogin(@Param("loginId") String loginID, @Param("loginPw") String loginPw) {return memberMapper.checkLogin(loginID,loginPw);}

    public List<Member> modifyMemberDetail(@Param("id_member") int idMember,
                                           @Param("nameMember") String nameMember,
                                           @Param("emailMember") String emailMember){return memberMapper.modifyMemberDetail(idMember,nameMember,emailMember);}

    public String deleteMember(@Param("idMember") int idMember){return memberMapper.deleteMember(idMember);}

    public List<Member> getMember(@Param("idMember") int id_member){return memberMapper.getMember(id_member);}

    public Member getMMember(@Param("idMember") int idMember){return  memberMapper.getMMember(idMember);}

    public int getAllManager(){return memberMapper.getAllManager();}
    public List<Member> getManagerMember(@Param("beginpage")int beginpage,
                                         @Param("endpage") int endpage,
                                         @Param("page") int page){return memberMapper.getManagerMember(beginpage,endpage,page);}

    public Member getuserName(@Param("userName") String userName){return memberMapper.getuserName(userName);}

}
