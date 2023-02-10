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

    public int getAllCount(){return boardMapper.getAllCount();}

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
                                     @Param("contentBoard") String contentBoard){return boardMapper.postingUpload(typeBoard,titleBoard,contentBoard);}

    public List<Board> getDetailBoard(@Param("idBoard") int idBoard){return boardMapper.getDetailBoard(idBoard);}

    public List<Board> postModifyBoard(@Param("idBoard") int idBoard,
                                @Param("typeBoard") String typeBoard,
                                @Param("titleBoard") String titleBoard,
                                @Param("contentBoard") String contentBoard){return boardMapper.postModifyBoard(idBoard,typeBoard,titleBoard,contentBoard);}
}
