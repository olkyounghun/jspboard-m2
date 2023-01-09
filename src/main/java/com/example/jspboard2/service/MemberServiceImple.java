package com.example.jspboard2.service;

import com.example.jspboard2.domain.Member;
import com.example.jspboard2.mapper.MemberMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImple implements MemberService {

    private final MemberMapper memberMapper;

    public MemberServiceImple(MemberMapper memberMapper){this.memberMapper = memberMapper; }

    public Member getMembership(@Param("userMember") String userMember,
                                @Param("userPw") String userPw,
                                @Param("userName") String userName,
                                @Param("userGender") Integer userGender,
                                @Param("userEmailComplet") String userEmailComplet,
                                @Param("emailChk") Integer emailChk){

        return memberMapper.getMembership(userMember, userPw, userName, userGender, userEmailComplet, emailChk);
    }

}
