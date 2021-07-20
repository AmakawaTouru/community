package com.zhl.community.service;

import com.zhl.community.mapper.UserMapper;
import com.zhl.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RandomCodeService randomCodeService;
    @Autowired
    private MailService mailService;

    /**
     * 根据电子邮箱查找用户
     * @param email
     * @return
     */
    public User findByEmail(String email) {
        return userMapper.findByEmail(email);
    }


    /**
     * 给电子邮箱发送一个activeCode
     * @param email
     * @param activeCode
     * @return
     */
    public String sendEmail(String email, String activeCode) {
        //创建激活码
        String code = randomCodeService.createActiveCode();
        //主题
        String subject = "来自安洁社区的激活邮件";
        String context = "<a href=\"\">请在五分钟内完成注册 "+activeCode+":"+code+"</a>";
        //发送激活邮件
        mailService.sendMimeMail (email,subject,context);
        return code;
    }

    /**
     * 创建一个User
     * @param user
     */
    public void create(User user) {
        //插入
        user.setGmtCreate(System.currentTimeMillis());
        user.setGmtModified(user.getGmtCreate());
        userMapper.insert(user);
    }

    /**
     * 根据用户ID，更新用户内容
     * @param user
     */
    public void updateByPrimaryKey(User user) {
        userMapper.updateByPrimaryKey(user);
    }

    public User findByEmailAndPass(String email,String password) {
        return userMapper.findByEmailAndPass(email,password);
    }
}
