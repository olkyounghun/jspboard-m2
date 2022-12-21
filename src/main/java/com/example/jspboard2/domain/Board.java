package com.example.jspboard2.domain;

import lombok.Data;

import java.sql.Date;

@Data
public class Board {

    private Integer ID_BOARD;
    private String TYPE_BOARD;
    private String TITLE_BOARD;
    private String CONTENT_BOARD;
    private String USER_BOARD;
    private Date REGDATE_BOARD;
    private Date MODDATE_BOARD;

}
