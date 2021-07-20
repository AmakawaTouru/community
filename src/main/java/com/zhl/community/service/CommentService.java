package com.zhl.community.service;

import com.zhl.community.dto.CommentDTO;
import com.zhl.community.dto.NotificationDTO;
import com.zhl.community.enums.CommentTypeEnum;
import com.zhl.community.enums.NotificationStatusEnum;
import com.zhl.community.enums.NotificationTypeEnum;
import com.zhl.community.exception.CustomizeErrorCode;
import com.zhl.community.exception.CustomizeException;
import com.zhl.community.mapper.CommentMapper;
import com.zhl.community.mapper.NotificationMapper;
import com.zhl.community.mapper.QuestionMapper;
import com.zhl.community.mapper.UserMapper;
import com.zhl.community.model.Comment;
import com.zhl.community.model.Notification;
import com.zhl.community.model.Question;
import com.zhl.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private NotificationMapper notificationMapper;

    /**
     * 增加一条回复
     * @param comment
     * @param user
     */
    @Transactional
    public void addComment(Comment comment, User user) {
        //如果没有问题ID，抛异常
        if (comment.getParentId() == null || comment.getParentId() == 0) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }

        //如果没有不存在的回复类型，抛异常
        if (comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);

        }
        if (comment.getType() == CommentTypeEnum.COMMENT.getType()) {
            //回复评论
            //获取父节点评论信息
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            System.out.println(dbComment);
            //如果主键空，抛异常
            if (dbComment == null) {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            // 获取到父节点评论信息对应的问题消息
            Question question = questionMapper.findById(dbComment.getParentId());
            //如果问题ID为空，抛异常
            if (question == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            //插入这一条评论
            commentMapper.insert(comment);
            //给评论的父节点增加一个评论数
            Comment parentComment = new Comment();
            parentComment.setId(comment.getParentId());
            parentComment.setCommentCount(1);
            //给父节点增加一个评论数
            commentMapper.incCommentCount(parentComment);
            //创建通知
            createNotification(comment,dbComment.getCommentator(), user.getName(), question.getTitle(), NotificationTypeEnum.REPLY_COMMENT, question.getId());

        } else {
            //回复问题
            Question question = questionMapper.findById(comment.getParentId());
            if (question == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            comment.setCommentCount(0);
            commentMapper.insert(comment);
            question.setCommentCount(1);
            //用来给问题增加一个回复
            questionMapper.incCommentCount(question);
            //创建通知
            createNotification(comment, question.getCreator(), user.getName(), question.getTitle(), NotificationTypeEnum.REPLY_QUESTION, question.getId());
        }
    }

    /**
     * 根据问题ID，查询问题ID内部的回复
     * @param id
     * @param questionEunm
     * @return
     */
    public List<CommentDTO> listCommentById(Integer id, CommentTypeEnum questionEunm) {
        List<Comment> comments = commentMapper.selectByPrimaryKeyDesc(id, questionEunm.getType());
        System.out.println(comments);
//        //用Set找出不重复的Uid
        Map<Integer,User> umap = new HashMap<>();
//        //根据Uid找出所有的User消息
        for(Comment c:comments){
            User user = userMapper.findById(c.getCommentator());
            umap.put(c.getCommentator(),user);
        }
//        //包装到CommentDTO里面
        List<CommentDTO> res = new ArrayList<>();
        for(Comment c:comments){
            CommentDTO commentDTO = new CommentDTO();
            //快速复制值
            BeanUtils.copyProperties(c, commentDTO);
            commentDTO.setUser(umap.get(c.getCommentator()));
            res.add(commentDTO);
        }
        return res;
    }


    /**
     * 创建一个通知
     * @param comment 评论的内容
     * @param receiver 收到通知人的ID
     * @param notifierName 通知人的姓名
     * @param outerTitle   问题的标题
     * @param notificationType 通知的类型
     * @param outerId 问题的ID
     */
    public void createNotification(Comment comment, Integer receiver, String notifierName, String outerTitle, NotificationTypeEnum notificationType, Integer outerId){
        //如果接收方和评论的创造者是一致的话，就不通知了。
        if (receiver == comment.getCommentator() && (notificationType.getType() == 1 || notificationType.getType() == 2)) {
            return;
        }
        Notification notification = new Notification();
        notification.setGmtCreate(System.currentTimeMillis());
        notification.setType(notificationType.getType());
        notification.setNotifier(comment.getCommentator());
        notification.setNotifierName(notifierName);
        notification.setOuterId(outerId);
        notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());
        notification.setReceiver(receiver);
        notification.setOuterTitle(outerTitle);
        notificationMapper.inser(notification);
    }





}
