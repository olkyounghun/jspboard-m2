package com.example.jspboard2.domain;

import lombok.Data;

@Data
public class Search {

    private Integer ID_SEARCH;
    private String CONTENT_SEARCH; // 검색하는 내용
    private String RESULT_SEARCH; // 검색된 내용에 따른 리턴 페이지 ex) 목록 > list.jsp

}
