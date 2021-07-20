package com.zhl.community.model;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String accpuntId;
    private String name;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;
    private String email;
    private String password;
}
