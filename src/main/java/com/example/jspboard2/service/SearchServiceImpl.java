package com.example.jspboard2.service;

import com.example.jspboard2.domain.Search;
import com.example.jspboard2.mapper.SearchMapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@MapperScan(value = "com.example.jspboard2.mapper.SearchMapper")
public class SearchServiceImpl implements SearchService{

    private final SearchMapper searchMapper;

    @Autowired
    public SearchServiceImpl(SearchMapper searchMapper) {
        this.searchMapper = searchMapper;
    }

    public String start(@Param("startword") String startword){ return searchMapper.start(startword);} // 메인 화면에서 검색시 동작
}
