package com.example.jspboard2.mapper;

import com.example.jspboard2.domain.Board;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {

    String start(@Param("search") String search); // 메인 화면에서 검색시 동작
    List<Board> getBoardList(); // 목록 화면에서 게시물들 출력

    List<Board> getSearchResult(@Param("startDate") String startDate, // 목록화면의 검색창에서 검색시 검색된 내용들 출력
                                @Param("endDate") String endDate,
                                @Param("searchType") String searchType,
                                @Param("searchName") String searchName);

}
