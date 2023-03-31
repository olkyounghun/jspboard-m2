package com.example.jspboard2.service;

import com.example.jspboard2.domain.Board;
import com.example.jspboard2.mapper.BoardMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService{

    private final BoardMapper boardMapper;

    @Autowired
    public BoardServiceImpl(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    public int getAllCount(){return boardMapper.getAllCount();} // 목록 전체글 수 파악 후 전체페이지 설정에 이용

    public int getNewBoardId(){return  boardMapper.getNewBoardId();} // 작성된 글의 글번호를 가져오기 위함.

    public List<Board> getBoardList(@Param("beginpage")int beginpage,
                                    @Param("endpage") int endpage,
                                    @Param("page") int page){return boardMapper.getBoardList(beginpage,endpage,page);} // 목록 화면에서 게시물들 출력

    public List<Board> getSearchResult(@Param("startDate") String startDate, // 목록화면의 검색창에서 검색시 검색된 내용들 출력
                                       @Param("endDate") String endDate,
                                       @Param("searchType") String searchType,
                                       @Param("searchName") String searchName){
        return boardMapper.getSearchResult(startDate, endDate, searchType,searchName);
    }

    public List<Board> postingUpload(@Param("typeBoard") String typeBoard,
                                     @Param("titleBoard") String titleBoard,
                                     @Param("contentBoard") String contentBoard,
                                     @Param("userBoard") String userBoard,
                                     @Param("idMember") Long idMember){return boardMapper.postingUpload(typeBoard,titleBoard,contentBoard,userBoard,idMember);}

    public List<Board> getDetailBoard(@Param("id_board") int id_board){return boardMapper.getDetailBoard(id_board);} // 글 보기로 보여질 해당 회원의 글

    public Board getMatchPoint(@Param("id_board") int id_board){return boardMapper.getMatchPoint(id_board);}

    public List<Board> postModifyBoard(@Param("idBoard") int idBoard,
                                @Param("typeBoard") String typeBoard,
                                @Param("titleBoard") String titleBoard,
                                @Param("contentBoard") String contentBoard){return boardMapper.postModifyBoard(idBoard,typeBoard,titleBoard,contentBoard);}
}
