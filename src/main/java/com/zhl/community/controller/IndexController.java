package com.zhl.community.controller;

import com.zhl.community.dto.AccessTokenDTO;
import com.zhl.community.dto.GithubUser;
import com.zhl.community.dto.PaginationDTO;
import com.zhl.community.dto.QuestionDTO;
import com.zhl.community.model.Question;
import com.zhl.community.model.User;
import com.zhl.community.provider.GithubProvider;
import com.zhl.community.service.LoginService;
import com.zhl.community.service.QuestionService;
import com.zhl.community.service.TagValuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private LoginService loginService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private TagValuesService tagValuesService;


    @Value("${github.client.id}")
    private String id;
    @Value("${github.client.seret}")
    private String serect;
    @Value("${github.redirect.uri}")
    private String uri;


    @RequestMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "7") Integer size,
                        @RequestParam(name = "search",required = false) String search,
                        @RequestParam(name = "sort", required = false) String sort
                        ){
        //搜索框内容（非必须）
        model.addAttribute("search", search);
        model.addAttribute("sort",sort);
        //给首页页面显示热门标签
        List<String> tags = tagValuesService.hogTags();
        //分页显示问题信息
        PaginationDTO<QuestionDTO>  pagination = questionService.listAllQuestion(page,size,search,sort);
        model.addAttribute("pagination",pagination);
        model.addAttribute("tags",tags);
        return "index";
    }

    @RequestMapping("/callback")
    public String callback(@RequestParam("code") String code,
                           @RequestParam("state") String state,
                           HttpSession session,
                           HttpServletResponse response,
                           Model model){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(id);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setClient_secret(serect);
        accessTokenDTO.setRedirect_uri(uri);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        System.out.println(accessToken);
        GithubUser ghUser = githubProvider.getUser(accessToken);
        User user = loginService.login(ghUser);
        session.setAttribute("user", user);
        //增加一个cookie的token
        response.addCookie(new Cookie("token", user.getToken()));
        return "redirect:/";
    }


}
