package com.zhl.community.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ConnQuestionTagvMapper {

    void deleteByQuestionId(Integer id);

    void insert(@Param("qid") Integer id,@Param("vid") int v);

    List<Integer> selectTvidsByQid(int qid);

    List<Integer> selectQidsByTvid(@Param("qid") int qid,@Param("list") List<Integer> tvids);
}
