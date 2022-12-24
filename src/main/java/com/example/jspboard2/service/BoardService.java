package com.example.jspboard2.service;

import com.example.jspboard2.domain.Board;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BoardService {

    String start(@Param("search") String search);

    List<Board> getBoardList();

    List<Board> getSearchResult(@Param("startDate") String startDate,
                                @Param("endDate") String endDate,
                                @Param("searchType") String searchType,
                                @Param("searchName") String searchName);

}
