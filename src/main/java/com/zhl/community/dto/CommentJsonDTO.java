package com.zhl.community.dto;

import lombok.Data;

@Data
public class CommentJsonDTO {
    private Integer parentId;
    private String content;
    private Integer type;
}
