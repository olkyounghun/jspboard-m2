package com.example.jspboard2.domain;

import lombok.Data;

import java.sql.Date;

@Data
public class Member {

    private int id_member; // 멤버 ID
    private String user_member; // 멤버 작성되는 아이디정보(아이디)
    private String password_member; // 멤버 비밀번호
    private String name_member; // 멤버 이름
    private String email_member; // 멤버 이메일
    private boolean emailcheck_member; // 이메일 수신 체크여부
    private int sex_member; // 멤버 성별
    private Date regdate_member; // 멤버 회원가입날짜


}
