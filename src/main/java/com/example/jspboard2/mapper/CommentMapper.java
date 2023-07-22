package com.example.jspboard2.mapper;

import com.example.jspboard2.domain.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CommentMapper {

    Comment getComment(@Param("title_comment") String title_comment,
                       @Param("content_comment") String content_comment);

}
