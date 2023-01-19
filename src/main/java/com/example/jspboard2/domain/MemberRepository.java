package com.example.jspboard2.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>(); // static 사용
    private static long sequence = 0L; // static 사용 // 0L 는 무엇인가??

    public Member save(Member member) {
        member.setId_member(++sequence);
        log.info("save : member={}", member);
        store.put(member.getId_member(), member); // store의 사용하는 이유와 시퀀스를 새롭게 주는 이유는??
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public Optional<Member> findByLoginId(String loginId) {
        /*List<Member> all = findAll();
        for (Member m : all) {
            if (m.getLoginId().equals(loginId)) {
                return Optional.of(m);
            }
        }
        return Optional.empty();*/
        return findAll().stream() // stream ??
                .filter(m -> m.getUser_member().equals(loginId)).findFirst();  // findFirst ??
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    } // store 를 사용해서 리턴 해주는 이유??

    public void clearStore() {
        store.clear();
    }
}