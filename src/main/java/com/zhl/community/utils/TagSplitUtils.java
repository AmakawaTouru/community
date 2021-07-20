package com.zhl.community.utils;

public class TagSplitUtils {

    /**
     * 用于切分标签
     * @param tags
     * @return
     */
    public static String[] tagSplit(String tags){
        String[] res = tags.split(",");
        return res;
    }
}
