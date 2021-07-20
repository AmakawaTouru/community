package com.zhl.community.mapper;

import com.zhl.community.enums.NotificationStatusEnum;
import com.zhl.community.model.Notification;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NotificationMapper {

    void inser(Notification notification);

    List<Notification> selectUnreadById(@Param("id") Integer id,@Param("status") int status);

    List<Notification> selectByUid(Integer id);

    Notification selectById(Integer id);

    void updateByPrimaryKey(Notification notification);
}
