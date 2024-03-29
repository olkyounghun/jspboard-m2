package com.example.jspboard2.domain;

import lombok.Data;

import java.sql.Date;

@Data
public class Member {

    private Long id_member; // 멤버 ID
    private String user_member; // 멤버 작성되는 아이디정보(아이디)
    private String password_member; // 멤버 비밀번호
    private String name_member; // 멤버 이름
    private String email_member; // 멤버 이메일
    private String emailcheck_member; // 이메일 수신 체크여부 true default
    private String gender_member; // 멤버 성별
    private Date regdate_member; // 멤버 회원가입날짜
    private Integer rating_member; // 멤버 등급 기본회원일겨우 '2' default

    private Integer fucku;
    private String what_user;

    public void setMember(String user_member,String password_member, String name_member, String email_member, String gender_member){
        this.user_member = user_member;
        this.password_member = password_member;
        this.name_member = name_member;
        this.email_member = email_member;
        this.gender_member = gender_member;
    }
    public void printValue(){
        System.out.println("user_member : " + user_member);
        System.out.println("password_member : " + password_member);
        System.out.println("name_member : " + name_member);
        System.out.println("email_member : " + email_member);
        System.out.println("gender_member : " + gender_member);

    }

}
