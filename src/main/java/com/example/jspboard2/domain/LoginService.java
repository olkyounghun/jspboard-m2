package com.example.jspboard2.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    public Member login(String loginId, String password) {
        /*Optional<Member> findMemberOptional = memberRepository.findByLoginId(loginId);
        Member member = findMemberOptional.get();
        if (member.getPassword().equals(password)) {
            return member;
        } else return null;*/
        return memberRepository.findByLoginId(loginId)
                .filter(m -> m.getPassword_member().equals(password))
                .orElse(null); // orElse ??
    }
}
