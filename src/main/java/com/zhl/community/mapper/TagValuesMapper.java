package com.zhl.community.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TagValuesMapper {
    
    Integer selectByValue(@Param("val") String s);

    List<String> selectByIds(List<Integer> hotTags);
}
