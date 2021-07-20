package com.zhl.community.dto;

import com.zhl.community.exception.CustomizeErrorCode;
import com.zhl.community.exception.CustomizeException;
import lombok.Data;

@Data
public class JsonResultDTO<T> {
    private Integer code;
    private String message;
    private T data;

    /**
     * 错误状态信息
     * @param code
     * @param message
     * @return
     */
    public static JsonResultDTO errorOf(Integer code, String message){
        JsonResultDTO jsonResultDTO = new JsonResultDTO();
        jsonResultDTO.setCode(code);
        jsonResultDTO.setMessage(message);
        return jsonResultDTO;
    }

    public static JsonResultDTO errorOf(CustomizeErrorCode customizeErrorCode){
        return errorOf(customizeErrorCode.getCode(), customizeErrorCode.getMessage());
    }

    public static JsonResultDTO errorOf(CustomizeException e){
        return errorOf(e.getCode(), e.getMessage());
    }

    /**
     * 返回的JSON成功状态信息
     * @return
     */
    public static JsonResultDTO okOf(){
        JsonResultDTO jsonResultDTO = new JsonResultDTO();
        jsonResultDTO.setCode(200);
        jsonResultDTO.setMessage("Request success!");
        return jsonResultDTO;
    }

    /**
     * 成功返回的JSON状态信息
     * @param t
     * @param <T>
     * @return
     */
    public static <T> JsonResultDTO okOf(T t){
        JsonResultDTO jsonResultDTO = new JsonResultDTO();
        jsonResultDTO.setCode(200);
        jsonResultDTO.setMessage("Request success!");
        jsonResultDTO.setData(t);
        return jsonResultDTO;
    }



}
