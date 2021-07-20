package com.zhl.community.controller;

import com.zhl.community.dto.JsonResultDTO;
import com.zhl.community.model.User;
import com.zhl.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String register() {
        return "register";
    }


    @PostMapping("/register")
    public String registering(HttpServletRequest request, HttpServletResponse response, Model model) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String code = request.getParameter("code");
        boolean flag = false;
        Cookie[] cookies = request.getCookies();
        User user = new User();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("activeCode")) {
                String value = cookie.getValue();
                if (value.equals(code)) {
                    flag = true;
                    break;
                }
            }
        }
        //需要flag正确才能够注册成功
        if (flag) {
            user.setName(email);
            user.setPassword(password);
            user.setAccpuntId(email);
            user.setEmail(email);
            user.setAvatarUrl("https://oss-anjieliya-guangzhou.oss-cn-guangzhou.aliyuncs.com/5391084a-2e89-4614-a03b-0219a926e152.png?Expires=1962631209&OSSAccessKeyId=LTAI5t6LtFY5QprwdNW7rMUY&Signature=6fZwTmv7xV1KrTgnFBTQmh%2FWVno%3D");
            userService.create(user);
            model.addAttribute("signupSuccess", "success");
            return "register";
        } else {
            //注册失败
            model.addAttribute("signupFail", "Fail");
            return "register";
        }

    }





    @ResponseBody
    @GetMapping("/sendActiveEmail/{email}")
    public JsonResultDTO sendActiveEmail(@PathVariable(name = "email") String email, HttpServletResponse response) {
        //使用cookie存储验证码用于校验
        User user = userService.findByEmail(email);
        //如果邮箱已经存在
        if (user != null) {
            return JsonResultDTO.errorOf(300,"邮箱已经注册过了");
        }
        //生成随机Code
        String activeCode = userService.sendEmail(email,"ActiveCode");
        Cookie cookie = new Cookie("activeCode", activeCode);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 5);
        response.addCookie(cookie);
        return JsonResultDTO.okOf(activeCode);
    }


}
