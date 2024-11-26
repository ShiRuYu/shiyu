package com.shiyu.common.utils;

import lombok.Data;

@Data
public class Result<T> {
    /**
     * 查询数据
     */
    private T data;

    /**
     * 响应码
     */
    private int code = 200;

    /**
     * 描述
     */
    private String message = "true";
}
