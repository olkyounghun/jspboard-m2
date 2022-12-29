package com.example.jspboard2.domain;

import lombok.Data;

@Data
public class Search {

    private Integer id_search;
    private String content_search; // 검색하는 내용
    private String result_search; // 검색된 내용에 따른 리턴 페이지 ex) 목록 > list.jsp

}
