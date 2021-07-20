package com.zhl.community.controller;

import com.zhl.community.mapper.UserMapper;
import com.zhl.community.model.User;
import com.zhl.community.provider.OssClientAuthorization;
import com.zhl.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class ModifyProfileController {

    @Autowired
    private UserService userService;
    @Autowired
    private OssClientAuthorization ossClientAuthorization;


    @GetMapping("/modifyProfile")
    public String showProfile(HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            return "redirect:/";
        }
        model.addAttribute("user", user);
        return "profile_info";
    }

    @PostMapping("/modifyProfile")
    public String modifyProfile(HttpServletRequest request,Model model) throws IOException {
        //将request转换成多部分请求（因为有可能文件大）
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        MultipartFile file =    multipartHttpServletRequest.getFile("head_portrait");
        String uploadImageUrl = ossClientAuthorization.upload(file.getInputStream(), file.getOriginalFilename());
        User user = (User) request.getSession().getAttribute("user");
        user.setAvatarUrl(uploadImageUrl);
        userService.updateByPrimaryKey(user);
        return "profile_info";
    }


}
