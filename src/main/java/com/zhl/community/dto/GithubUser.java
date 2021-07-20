package com.zhl.community.dto;

import lombok.Data;

@Data
public class GithubUser {
    private String login;
    private String id;
    private String bio;
    private String avatar_url;
}
