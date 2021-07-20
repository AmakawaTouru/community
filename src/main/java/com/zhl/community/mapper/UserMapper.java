package com.zhl.community.mapper;

import com.zhl.community.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> findAll();

    User findByAccId(String id);

    void addUser(User user);

    User findByToken(String token);

    User findById(int id);

    User findByEmail(String email);

    void insert(User user);

    void updateByPrimaryKey(User user);

    User findByEmailAndPass(@Param("email") String email,@Param("password") String password);

}
