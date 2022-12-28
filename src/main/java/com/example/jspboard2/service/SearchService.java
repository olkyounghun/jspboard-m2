package com.example.jspboard2.service;

import org.apache.ibatis.annotations.Param;

public interface SearchService {

    String getSearchContent(@Param("startword") String startword); // 메인 화면에서 검색시 동작
}
