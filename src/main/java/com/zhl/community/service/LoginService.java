package com.zhl.community.service;

import com.zhl.community.dto.GithubUser;
import com.zhl.community.mapper.UserMapper;
import com.zhl.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LoginService {

    @Autowired
    private UserMapper userMapper;


    /**
     * 点击登录时，根据GitHubID执行
     * @param ghUser
     * @return
     */
    public User login(GithubUser ghUser){
        User user = userMapper.findByAccId(ghUser.getId());
        System.out.println(ghUser.getId());
        //如果没有注册过
        if(user == null){
            //如果没有注册过
            user = new User();
            user.setAccpuntId(ghUser.getId());
            user.setName(ghUser.getLogin());
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            user.setToken(UUID.randomUUID().toString());
            user.setAvatarUrl(ghUser.getAvatar_url());
            userMapper.addUser(user);
        }
        return user;
    }


    /**
     * 根据token来返回用户
     * @param token
     * @return
     */
    public User login(String token){
        User user = userMapper.findByToken(token);
        return user;
    }

}
