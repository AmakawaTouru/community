package com.zhl.community.controller;

import com.zhl.community.model.User;
import com.zhl.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }


    @PostMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response, Model model){
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String rememberFlag = request.getParameter("rememberFlag");
        User user = userService.findByEmailAndPass(email,password);
        if (user != null) {
            //设置Token
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            userService.updateByPrimaryKey(user);
            Cookie cookie = new Cookie("token", token);
            cookie.setPath("/");
            if (rememberFlag != null) {
                cookie.setMaxAge(60 * 60 * 24);
            }
            response.addCookie(cookie);
            return "redirect:/";
        } else {
            //登录失败
            model.addAttribute("loginFail", "fail");
            return "login";
        }
    }

    @GetMapping("/loginout")
    public String loginOut(HttpServletRequest request,
                           HttpServletResponse response,
                           Model model){
        request.getSession().removeAttribute("user");
        Cookie token = new Cookie("token", null);
        token.setMaxAge(0);
        response.addCookie(token);
        return "redirect:/";
    }

}
