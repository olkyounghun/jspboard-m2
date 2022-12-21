package com.example.jspboard2.mapper;

import com.example.jspboard2.domain.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    List<Board> getBoardList();

}
