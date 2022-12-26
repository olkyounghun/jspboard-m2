package com.example.jspboard2.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SearchMapper {

    String start(@Param("startword") String startword); // 메인 화면에서 검색시 동작

}
