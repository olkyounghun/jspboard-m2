package com.example.jspboard2.mapper;

import com.example.jspboard2.domain.Board;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {

    int getAllCount();

    List<Board> getBoardList(@Param("beginpage")int beginpage,
                             @Param("endpage") int endpage,
                             @Param("page") int page); // 목록 화면에서 게시물들 출력

    List<Board> getSearchResult(@Param("startDate") String startDate, // 목록화면의 검색창에서 검색시 검색된 내용들 출력
                                @Param("endDate") String endDate,
                                @Param("searchType") String searchType,
                                @Param("searchName") String searchName);

    List<Board> postingUpload(@Param("typeBoard") String typeBoard,
                              @Param("titleBoard") String titleBoard,
                              @Param("contentBoard") String contentBoard);

    List<Board> getDetailBoard(@Param("idBoard") int idBoard);
}
