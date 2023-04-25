package com.example.jspboard2.service;

import com.example.jspboard2.domain.Board;
import com.example.jspboard2.mapper.BoardMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService{

    private final BoardMapper boardMapper;

    @Autowired
    public BoardServiceImpl(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    public int getAllCount(){return boardMapper.getAllCount();} // 목록 전체글 수 파악 후 전체페이지 설정에 이용

    public int getSearchAllCount(@Param("searchType") String searchType,
                                 @Param("startDate") String startDate,
                                 @Param("endDate") String endDate,
                                 @Param("searchName") String searchName){return boardMapper.getSearchAllCount(searchType,startDate,endDate,searchName);}

    public int getNewBoardId(){return  boardMapper.getNewBoardId();} // 작성된 글의 글번호를 가져오기 위함.

    public List<Board> getBoardList(){return boardMapper.getBoardList();} // 목록 화면에서 게시물들 출력

    public List<Board> getPageList(@Param("pagelist") int pagelist){return boardMapper.getPageList(pagelist);}

    public List<Board> getSearchResult(@Param("searchType") String searchType,
                                       @Param("startDate") String startDate,
                                       @Param("endDate") String endDate,
                                       @Param("searchName") String searchName){

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("searchName", searchName);
        map.put("endDate", endDate);
        map.put("startDate", startDate);
        map.put("searchType", searchType);

        return boardMapper.getSearchResult(map);
    }

    public List<Board> getSearchPageResult(@Param("searchType") String searchType,
                                           @Param("startDate") String startDate,
                                           @Param("endDate") String endDate,
                                           @Param("searchName") String searchName,
                                           @Param("pagelist") int pagelist){return boardMapper.getSearchPageResult(searchType,startDate,endDate,searchName,pagelist);};

    public List<Board> postingUpload(@Param("typeBoard") String typeBoard,
                                     @Param("titleBoard") String titleBoard,
                                     @Param("contentBoard") String contentBoard,
                                     @Param("userBoard") String userBoard,
                                     @Param("idMember") Long idMember){return boardMapper.postingUpload(typeBoard,titleBoard,contentBoard,userBoard,idMember);}

    public List<Board> getDetailBoard(@Param("id_board") int id_board){return boardMapper.getDetailBoard(id_board);} // 글 보기로 보여질 해당 회원의 글

    public Board viewUpPoint(@Param("idBoard") int idBoard){return boardMapper.viewUpPoint(idBoard);    }

    public Board getMatchPoint(@Param("id_board") int id_board){return boardMapper.getMatchPoint(id_board);}

    public List<Board> postModifyBoard(@Param("typeBoard") String typeBoard,
                                       @Param("titleBoard") String titleBoard,
                                       @Param("contentBoard") String contentBoard,
                                       @Param("idBoard") int idBoard){return boardMapper.postModifyBoard(typeBoard,titleBoard,contentBoard,idBoard);}

    public List<Board> boardDeleteAction(@Param("id_board") int idBoard){return boardMapper.activeDeleteBoard(idBoard);}

}
