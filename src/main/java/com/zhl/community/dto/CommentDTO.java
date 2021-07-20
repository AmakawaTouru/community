package com.zhl.community.dto;

import com.zhl.community.model.Question;
import com.zhl.community.model.User;
import lombok.Data;

@Data
public class CommentDTO {
    private Integer id;
    private Integer parentId;
    private Integer type;
    private Integer commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer likeCount;
    private String content;
    private Integer commentCount;
    private User user;
    private Question question;
}
