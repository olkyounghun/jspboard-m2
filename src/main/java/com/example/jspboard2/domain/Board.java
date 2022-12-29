package com.example.jspboard2.domain;

import lombok.Data;

import java.sql.Date;

@Data
public class Board {

    private Integer id_board; // board id
    private String type_board; // 작성된 게시물의 카테고리 타입
    private String title_board; // 작성된 게시물의 제목
    private String content_board; // 작성된 게시물의 내용
    private String user_board; // 게시물 작성자
    private Integer views_board; // 게시물 조회수
    private Date regdate_board; // 게시물 등록된 최초의 날짜
    private Date moddate_board; // 게시물이 수정된 최근의 날짜

}
