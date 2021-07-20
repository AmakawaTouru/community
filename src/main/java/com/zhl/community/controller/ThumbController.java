package com.zhl.community.controller;

import com.zhl.community.enums.NotificationTypeEnum;
import com.zhl.community.mapper.CommentMapper;
import com.zhl.community.mapper.QuestionMapper;
import com.zhl.community.mapper.ThumbMapper;
import com.zhl.community.mapper.UserMapper;
import com.zhl.community.model.Comment;
import com.zhl.community.model.Question;
import com.zhl.community.model.Thumb;
import com.zhl.community.model.User;
import com.zhl.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ThumbController {
    @Autowired
    private ThumbMapper thumbMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/thumb/{thumbParentId}/{thumbId}/{questionId}", method = RequestMethod.GET)
    public String thumb(@PathVariable(name = "thumbParentId") Integer thumbParentId,
                        @PathVariable(name = "thumbId") Integer thumbId,
                        @PathVariable(name = "questionId") Integer questionId) {
        //参数3个：
        //评论ID
        //UserID
        //问题ID

        //根据评论ID和用户ID找到一个TotalCount
        Integer totalCount = thumbMapper.countByPidAndTid(thumbParentId,thumbId);
        //判断同一个用户对同一个评论是否点赞超过一次
        if (totalCount >= 1) {
            //根据评论ID查找点赞次数
            Integer count = thumbMapper.countByPid(thumbParentId);
            return "" + count;
        }


        Thumb thumb = new Thumb();
        thumb.setThumbId(thumbId);
        thumb.setThumbIdParent(thumbParentId);
        thumbMapper.insert(thumb);

        //根据评论ID找
        Integer count = thumbMapper.countByPid(thumbParentId);
        Comment comment = commentMapper.selectByPrimaryKey(thumbParentId);
        //创建点赞通知
        Question question = questionMapper.findById(questionId);
        User commentator = userMapper.findById(comment.getCommentator());
        commentService.createNotification(comment, comment.getCommentator(), commentator.getName(), question.getTitle(), NotificationTypeEnum.THUMB_COMMENT, question.getId());
        comment.setLikeCount(count);
        commentMapper.updateByPrimaryKey(comment);
        return "" + count;
    }

}
