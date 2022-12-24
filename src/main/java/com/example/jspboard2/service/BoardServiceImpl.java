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
    public BoardServiceImpl(BoardMapper boardmapper, BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    public String start(@Param("search") String search){ return boardMapper.start(search);};

    public List<Board> getBoardList(){return boardMapper.getBoardList();}

    public List<Board> getSearchResult(@Param("startDate") String startDate,
                                       @Param("endDate") String endDate,
                                       @Param("searchType") String searchType,
                                       @Param("searchName") String searchName){
        return boardMapper.getSearchResult(startDate, endDate, searchType,searchName);
    }
}
