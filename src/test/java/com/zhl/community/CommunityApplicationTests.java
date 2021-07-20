package com.zhl.community;

import com.zhl.community.mapper.UserMapper;
import com.zhl.community.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CommunityApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
    }

    @Test
    public void test(){
        List<User> all = userMapper.findAll();
        System.out.println(all);
    }


}
