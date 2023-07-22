package com.example.jspboard2.service;

import com.example.jspboard2.domain.Comment;
import org.apache.ibatis.annotations.Param;

public interface CommentService {

    Comment getComment(@Param("title_comment") String title_comment,
                       @Param("content_comment") String content_comment);
}
