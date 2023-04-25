package com.example.jspboard2.mapper;

import com.example.jspboard2.domain.Board;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface BoardMapper {

    int getAllCount();

    int getSearchAllCount(@Param("searchType") String searchType,
                          @Param("startDate") String startDate,
                          @Param("endDate") String endDate,
                          @Param("searchName") String searchName);

    int getNewBoardId();

    List<Board> getBoardList(); // 목록 화면에서 게시물들 출력

    List<Board> getPageList(@Param("pagelist") int pagelist);

    List<Board> getSearchResult(HashMap<String, String> map);

    List<Board> getSearchPageResult(@Param("searchType") String searchType,
                                    @Param("startDate") String startDate,
                                    @Param("endDate") String endDate,
                                    @Param("searchName") String searchName,
                                    @Param("pagelist") int pagelist);

    List<Board> postingUpload(@Param("typeBoard") String typeBoard,
                              @Param("titleBoard") String titleBoard,
                              @Param("contentBoard") String contentBoard,
                              @Param("userBoard") String userBoard,
                              @Param("idMember") Long idMember);

    List<Board> getDetailBoard(@Param("id_board") int id_board);

    Board viewUpPoint(@Param("idBoard") int idBoard);

    Board getMatchPoint(@Param("id_board") int id_board);

    List<Board> postModifyBoard(@Param("typeBoard") String typeBoard,
                                @Param("titleBoard") String titleBoard,
                                @Param("contentBoard") String contentBoard,
                                @Param("idBoard") int idBoard);

    List<Board> activeDeleteBoard(@Param("idBoard") int idBoard);

}
