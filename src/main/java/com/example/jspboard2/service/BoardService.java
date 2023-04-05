package com.example.jspboard2.service;

import com.example.jspboard2.domain.Board;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BoardService {

    int getAllCount();

    int getNewBoardId();

    List<Board> getBoardList(@Param("beginpage")int beginpage,
                             @Param("endpage") int endpage,
                             @Param("page") int page); // 목록 화면에서 게시물들 출력

    List<Board> getSearchResult(@Param("startDate") String startDate, // 목록화면의 검색창에서 검색시 검색된 내용들 출력
                                @Param("endDate") String endDate,
                                @Param("searchType") String searchType,
                                @Param("searchName") String searchName);

    List<Board> postingUpload(@Param("typeBoard") String typeBoard,
                              @Param("titleBoard") String titleBoard,
                              @Param("contentBoard") String contentBoard,
                              @Param("userBoard") String userBoard,
                              @Param("idMember") Long idMember);


    List<Board> getDetailBoard(@Param("id_board") int id_board); // 글보기에 들어갈 글 자료

    Board getMatchPoint(@Param("id_board") int id_board); // 로그인 아이디에 저장된 아이디번호와 작성된 글의 작성자아이디번호와 매치

    List<Board> postModifyBoard(@Param("typeBoard") String typeBoard,
                                @Param("titleBoard") String titleBoard,
                                @Param("contentBoard") String contentBoard,
                                @Param("idBoard") int idBoard);

    List<Board> boardDeleteAction(@Param("id_board") int idBoard);

}
