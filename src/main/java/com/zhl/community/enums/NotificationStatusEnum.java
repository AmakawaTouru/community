package com.zhl.community.enums;

/**
 * 通知的状态，0代表未读，1代表已读
 */
public enum NotificationStatusEnum {


    UNREAD(0),READ(1);

    private int status;

    NotificationStatusEnum(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
