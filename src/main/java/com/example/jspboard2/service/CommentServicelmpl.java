package com.example.jspboard2.service;

import com.example.jspboard2.domain.Comment;
import com.example.jspboard2.mapper.CommentMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServicelmpl implements CommentService{

    private final CommentMapper commentMapper;

    @Autowired
    public CommentServicelmpl(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    public Comment getComment(@Param("title_comment") String title_comment,
                       @Param("content_comment") String content_comment){return commentMapper.getComment(title_comment,content_comment);}

}
