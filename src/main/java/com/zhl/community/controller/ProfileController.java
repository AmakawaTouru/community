package com.zhl.community.controller;

import com.zhl.community.dto.NotificationDTO;
import com.zhl.community.dto.PaginationDTO;
import com.zhl.community.dto.QuestionDTO;
import com.zhl.community.mapper.NotificationMapper;
import com.zhl.community.model.User;
import com.zhl.community.service.NotificationService;
import com.zhl.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private NotificationService notificationService;


    @GetMapping("/profile/{action}")
    public String profile(HttpServletRequest request, @PathVariable(name = "action") String action, Model model,
                          @RequestParam(name = "page", defaultValue = "1") Integer page,
                          @RequestParam(name = "size", defaultValue = "7") Integer size){

        //如果没有登录，就回到主页
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }

        if ("questions".equals(action)) {
            PaginationDTO<QuestionDTO> pagination = questionService.listQuestionByUser(page, size, user);
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的问题");
            model.addAttribute("pagination",pagination);
        }
        if ("replies".equals(action)) {
            PaginationDTO<NotificationDTO> pagination = notificationService.list(page,size,user);
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "最新回复");
            model.addAttribute("pagination",pagination);
        } else if ("star".equals(action)) {
            model.addAttribute("section", "star");
            model.addAttribute("sectionName", "我的精华");
        }
        return "profile";


    }

}
