package com.example.jspboard2.domain;

import lombok.Data;

import java.sql.Date;

@Data
public class Board {

    private Integer ID_BOARD; // board ID
    private String TYPE_BOARD; // 작성된 게시물의 카테고리 타입
    private String TITLE_BOARD; // 작성된 게시물의 제목
    private String CONTENT_BOARD; // 작성된 게시물의 내용
    private String USER_BOARD; // 게시물 작성자
    private Date REGDATE_BOARD; // 게시물 등록된 최초의 날짜
    private Date MODDATE_BOARD; // 게시물이 수정된 최근의 날짜

}
