package com.zhl.community.controller;

import com.zhl.community.dto.CommentDTO;
import com.zhl.community.enums.CommentTypeEnum;
import com.zhl.community.dto.QuestionDTO;
import com.zhl.community.model.User;
import com.zhl.community.service.CommentService;
import com.zhl.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;

    /**
     * 展示问题列表，需要做以下几个
     *  1- 获取问题信息
     *  2- 获取相关问题
     *  3- 获取评论
     *  4- 获取历史
     * @param request
     * @param response
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/question/{id}")
    public String question(HttpServletRequest request,
                           HttpServletResponse response,
                           Model model,
                           @PathVariable(name = "id") Integer id){
        User user = (User) request.getSession().getAttribute("user");
        QuestionDTO questionDTO = questionService.listQuestionById(id);
        questionService.updateViewCount(id);
        List<CommentDTO> commentDTO = commentService.listCommentById(id, CommentTypeEnum.QUESTION);
        List<QuestionDTO> relatedQuestions = questionService.selectRelated(questionDTO);
        model.addAttribute("question",questionDTO);
        model.addAttribute("comments", commentDTO);
        model.addAttribute("relatedQuestions",relatedQuestions);
        return "question";
    }

}
