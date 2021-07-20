package com.zhl.community.utils;

public class StringUtils {

    public static boolean checkIsEmptyOrNull(String str){
        if(str.equals("") || str == null){
            return true;
        }
        return false;
    }
}
