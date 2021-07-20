package com.zhl.community.dto;

import com.zhl.community.model.Question;
import com.zhl.community.model.User;
import lombok.Data;

@Data
public class NotificationDTO {
    private Integer id;
    private Integer notifier;
    private Integer receiver;
    private Integer outerId;
    private Integer type;
    private Long gmtCreate;
    private Integer status;
    private String notifierName;
    private String outerTitle;

    private User ntfUser;
    private User recUser;
    private Question otQuestion;
    private String typeName;


}
