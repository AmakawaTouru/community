package com.zhl.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode{

    QUESTION_NOT_FOUND(2001, "没找到对应的问题ID"),
    NO_LOGIN(2003, "请登录账号"),
    SYS_ERROR(2004, "服务器过载啦，请重试"),
    TYPE_PARAM_WRONG(2005, "评价类型异常"),
    COMMENT_NOT_FOUND(2006, "没有找到回复"),
    COMMENT_IS_EMPTY(2007, "回复是空的"),
    READ_NOTIFICATION_FAIL(2008, "通知异常"),
    NOTIFICATION_NOT_FOUND(2009, "通知没有找到"),
    FILE_UPLOAD_FAILURE(2010,"文件上传失败"),
    TARGET_PARAM_NOT_FOUND(2021,"回复的父节点没找到");

    private String message;
    private Integer code;

    CustomizeErrorCode(Integer code,String message) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }
}
