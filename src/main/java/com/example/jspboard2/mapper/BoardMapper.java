package com.example.jspboard2.mapper;

import com.example.jspboard2.domain.Board;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {

    String start(@Param("search") String search);
    List<Board> getBoardList();

    List<Board> getSearchResult(@Param("startDate") String startDate,
                                @Param("endDate") String endDate,
                                @Param("searchType") String searchType,
                                @Param("searchName") String searchName);

}
