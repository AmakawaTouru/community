package com.zhl.community.controller;

import com.zhl.community.dto.CommentDTO;
import com.zhl.community.dto.CommentJsonDTO;
import com.zhl.community.enums.CommentTypeEnum;
import com.zhl.community.dto.JsonResultDTO;
import com.zhl.community.exception.CustomizeErrorCode;
import com.zhl.community.model.Comment;
import com.zhl.community.model.User;
import com.zhl.community.service.CommentService;
import com.zhl.community.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CommentController {


    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object post(HttpServletRequest request,
                       @RequestBody CommentJsonDTO commentJsonDTO){
        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            //没有登录，返回错误信息JSON
            return JsonResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        if(commentJsonDTO == null || StringUtils.checkIsEmptyOrNull(commentJsonDTO.getContent())){
            //JSON为空，返回错误信息
            return JsonResultDTO.errorOf(CustomizeErrorCode.COMMENT_IS_EMPTY);
        }
        Comment comment = new Comment();
        comment.setParentId(commentJsonDTO.getParentId());
        comment.setType(commentJsonDTO.getType());
        comment.setCommentator(user.getId());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setLikeCount(0);
        comment.setContent(commentJsonDTO.getContent());
        commentService.addComment(comment,user);
        //返回Json正确信息
        return JsonResultDTO.okOf();
    }

    /**
     * Ajax异步请求二级评论数据
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/comment/{id}", method = RequestMethod.GET)
    public JsonResultDTO<List<CommentDTO>> comments(@PathVariable(name = "id") Integer id) {
        List<CommentDTO> commentDTOS = commentService.listCommentById(id, CommentTypeEnum.COMMENT);
        return JsonResultDTO.okOf(commentDTOS);
    }



}
