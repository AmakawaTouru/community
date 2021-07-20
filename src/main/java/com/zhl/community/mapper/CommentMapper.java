package com.zhl.community.mapper;


import com.zhl.community.model.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {

    Comment selectByPrimaryKey(Integer parentId);

    void insert(Comment comment);

    void incCommentCount(Comment parentComment);

    List<Comment> selectByPrimaryKeyDesc(@Param("id") Integer id,@Param("type") Integer type);

    void updateByPrimaryKey(Comment comment);
}
