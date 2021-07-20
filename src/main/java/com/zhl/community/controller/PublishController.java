package com.zhl.community.controller;

import com.zhl.community.cache.TagCache;
import com.zhl.community.dto.QuestionDTO;
import com.zhl.community.model.User;
import com.zhl.community.service.PublishService;
import com.zhl.community.service.QuestionService;
import com.zhl.community.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class PublishController {

    @Autowired
    public PublishService publishService;

    @Autowired
    public QuestionService questionService;

    @GetMapping("/publish")
    public String publish(Model model){
        model.addAttribute("tags", TagCache.get());
        return "publish";
    }


    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            @RequestParam(value = "id",defaultValue = "-1") Integer id,
            HttpServletRequest request,
            Model model
    ){
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        //这里用于显示可选标签
        model.addAttribute("tags", TagCache.get());

        if(StringUtils.checkIsEmptyOrNull(title)){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if(StringUtils.checkIsEmptyOrNull(description)){
            model.addAttribute("error","问题描述不能为空");
            return "publish";
        }
        if(StringUtils.checkIsEmptyOrNull(tag)){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }

        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            model.addAttribute("error","请先登录！");
            return "publish";
        }

        //通过input的hidden隐藏了获取id的值 表单提交获取id值判断是编辑还是新键
        //如果ID为-1，说明是发布问题
        //如果ID为1，说明是修改问题
        publishService.publishOrUpdateQuestion(id,title, description, tag, user);

        return "redirect:/";
    }


    @GetMapping("/publish/{id}")
    public String editQuestion(HttpServletRequest request,
                               HttpServletResponse response,
                               Model model,
                               @PathVariable(name = "id") Integer id){
        QuestionDTO questionDTO = questionService.listQuestionById(id);
        model.addAttribute("title",questionDTO.getTitle());
        model.addAttribute("description",questionDTO.getDescription());
        model.addAttribute("tag",questionDTO.getTag());
        model.addAttribute("id",id);
        model.addAttribute("tags", TagCache.get());
        return "publish";
    }





}
