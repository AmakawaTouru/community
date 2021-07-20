package com.zhl.community.mapper;

import com.zhl.community.model.Thumb;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ThumbMapper {

    Integer countByPidAndTid(@Param("thumbParentId") Integer thumbParentId,@Param("thumbId") Integer thumbId);

    Integer countByPid(Integer thumbParentId);

    void insert(Thumb thumb);
}
