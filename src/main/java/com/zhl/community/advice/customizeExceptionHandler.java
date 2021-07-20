package com.zhl.community.advice;

import com.alibaba.fastjson.JSON;
import com.zhl.community.dto.JsonResultDTO;
import com.zhl.community.exception.CustomizeErrorCode;
import com.zhl.community.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
public class customizeExceptionHandler {
    /**
     * 用于异常处理
     * @param e
     * @param model
     * @param request
     * @param response
     * @return
     */
    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable e, Model model, HttpServletRequest request, HttpServletResponse response) {
        String contentType = request.getContentType();
        //json信息的错误跳转
        if ("application/json".equals(contentType)) {
            JsonResultDTO jsonResultDTO = null;
            //返回json
            if (e instanceof CustomizeException) {
                jsonResultDTO = jsonResultDTO.errorOf((CustomizeException) e);
            } else {
                jsonResultDTO = jsonResultDTO.errorOf(CustomizeErrorCode.SYS_ERROR);
            }

            try {
                response.setContentType("application/json");
                response.setStatus(200);
                response.setCharacterEncoding("utf-8");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(jsonResultDTO));
                writer.close();

            } catch (IOException ioe) {

            }
            return null;
        }
        //错误页面跳转
        if (e instanceof CustomizeException){
            model.addAttribute("message",e.getMessage());
        }else{
            model.addAttribute("message", CustomizeErrorCode.SYS_ERROR.getMessage());
        }
        e.printStackTrace();
        System.out.println(e.getMessage());
        return new ModelAndView("error");
    }
}

