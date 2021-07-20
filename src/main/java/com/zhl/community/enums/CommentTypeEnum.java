package com.zhl.community.enums;

public enum CommentTypeEnum {

    QUESTION(1),
    COMMENT(2);

    private Integer type;

    CommentTypeEnum(Integer type){
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    /**
     * 用来判断不存在的枚举类型
     * @param type
     * @return
     */
    public static boolean isExist(Integer type) {
        for (CommentTypeEnum commentTypeEnum : CommentTypeEnum.values()) {
            if (commentTypeEnum.getType() == type) {
                return true;
            }
        }
        return false;
    }


}
