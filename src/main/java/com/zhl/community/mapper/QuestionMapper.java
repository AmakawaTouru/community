package com.zhl.community.mapper;

import com.zhl.community.model.Question;
import com.zhl.community.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QuestionMapper {
    int inserQuestion(Question question);

    List<Question> findAll(@Param("search") String search,@Param("sort") String sort);

    List<Question> findByCreatorId(User user);

    Question findById(int id);

    void updateQuestion(Question question);

    void incCommentCount(Question question);

    List<Question> findByIds(List<Integer> qids);

    List<Question> selectByOffset(@Param("offset") int offset,@Param("limit") int limit);

    void updateViewCount(int id);
}
