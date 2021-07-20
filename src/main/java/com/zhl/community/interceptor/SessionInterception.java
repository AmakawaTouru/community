package com.zhl.community.interceptor;

import com.zhl.community.mapper.UserMapper;
import com.zhl.community.model.User;
import com.zhl.community.service.LoginService;
import com.zhl.community.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class SessionInterception implements HandlerInterceptor {

    @Autowired
    private LoginService loginService;
    @Autowired
    private NotificationService notificationService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取cookie，判断用户是否已经登陆过了。
        Cookie[] cookies = request.getCookies();
        User user = null;
        if(cookies != null){
            for(Cookie c:cookies){
                if(c.getName().equals("token")){
                    user = loginService.login(c.getValue());
                    break;
                }
            }
        }
        if(user != null){
            request.getSession().setAttribute("user", user);
            Integer unreadCount = notificationService.unreadCount(user.getId());
            request.getSession().setAttribute("unreadCount", unreadCount);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
