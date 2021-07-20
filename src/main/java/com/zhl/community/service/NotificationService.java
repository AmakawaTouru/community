package com.zhl.community.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhl.community.dto.NotificationDTO;
import com.zhl.community.dto.PaginationDTO;
import com.zhl.community.dto.QuestionDTO;
import com.zhl.community.enums.NotificationStatusEnum;
import com.zhl.community.enums.NotificationTypeEnum;
import com.zhl.community.exception.CustomizeErrorCode;
import com.zhl.community.exception.CustomizeException;
import com.zhl.community.mapper.NotificationMapper;
import com.zhl.community.model.Notification;
import com.zhl.community.model.Question;
import com.zhl.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;


    /**
     * 根据用户ID读取未读通知消息
     * @param id
     * @return
     */
    public Integer unreadCount(Integer id) {
        List<Notification> notifications = notificationMapper.selectUnreadById(id, NotificationStatusEnum.UNREAD.getStatus());
        return notifications.size();
    }


    /**
     * 返回通知列表
     * @param page
     * @param size
     * @param user
     * @return
     */
    public PaginationDTO<NotificationDTO> list(Integer page, Integer size, User user) {
        List<NotificationDTO> ntfdto = new ArrayList<>();
        PageHelper.startPage(page, size);
        List<Notification> notifications = notificationMapper.selectByUid(user.getId());
        PageInfo<Notification> pageInfo = new PageInfo<>(notifications);
        for(Notification n:notifications){
            NotificationDTO temp = new NotificationDTO();
            BeanUtils.copyProperties(n, temp);
            //返回类型信息
            temp.setTypeName(NotificationTypeEnum.nameOfType(n.getType()));
            temp.setRecUser(user);
            ntfdto.add(temp);
        }
        PaginationDTO<NotificationDTO> res = new PaginationDTO(pageInfo);
        res.setData(ntfdto);
        return res;
    }

    /**
     * 根据通知的ID来获取通知内容
     * 并且判断通知的收件人来判断是否正确
     * @param id
     * @param user
     * @return
     */
    public NotificationDTO read(Integer id, User user) {
        Notification notification = notificationMapper.selectById(id);
        //没找到通知
        if(notification == null){
            throw new CustomizeException(CustomizeErrorCode.NOTIFICATION_NOT_FOUND);
        }
        //用户ID和收件人ID不匹配
        if(!notification.getReceiver().equals(user.getId())){
            throw new CustomizeException(CustomizeErrorCode.READ_NOTIFICATION_FAIL);
        }
        notification.setStatus(NotificationStatusEnum.READ.getStatus());
        notificationMapper.updateByPrimaryKey(notification);
        NotificationDTO notificationDTO = new NotificationDTO();
        BeanUtils.copyProperties(notification, notificationDTO);
        notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
        return notificationDTO;
    }
}
