package com.example.jspboard2.domain;

import lombok.Data;

import java.sql.Date;

@Data
public class Comment {

    private Integer id_comment;
    private String title_comment;
    private String content_comment;
    private String user_comment;
    private Date regdate_commnet;
    private Date moddate_commnet;
    private Long id_member;

}
