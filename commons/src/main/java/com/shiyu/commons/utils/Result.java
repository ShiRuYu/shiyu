package com.shiyu.commons.utils;

import com.shiyu.commons.utils.exception.BizResultCode;
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
    private int code;

    /**
     * 描述
     */
    private String message;

    /**
     * 描述
     */
    private boolean success;


    public static <T> Result<T> success(BizResultCode resultCode) {
        Result<T> result = new Result<>();
        result.setCode(resultCode.getCode());
        result.setMessage(resultCode.getMsg());
        result.setSuccess(true);
        return result;
    }

    public static <T> Result<T> success(BizResultCode resultCode, T data) {
        Result<T> result = new Result<>();
        result.setData(data);
        result.setCode(resultCode.getCode());
        result.setMessage(resultCode.getMsg());
        result.setSuccess(true);
        return result;
    }

    public static <T> Result<T> fail(BizResultCode resultCode) {
        Result<T> result = new Result<>();
        result.setCode(resultCode.getCode());
        result.setMessage(resultCode.getMsg());
        result.setSuccess(false);
        return result;
    }

    public static <T> Result<T> fail(BizResultCode resultCode, T data) {
        Result<T> result = new Result<>();
        result.setData(data);
        result.setCode(resultCode.getCode());
        result.setMessage(resultCode.getMsg());
        result.setSuccess(false);
        return result;
    }

    public static <T> Result<T> success() {
        return success(BizResultCode.SUC);
    }

    public static <T> Result<T> success(T data) {
        return success(BizResultCode.SUC, data);
    }

    public static <T> Result<T> fail() {
        return fail(BizResultCode.ERR);
    }

    public static <T> Result<T> fail(T data) {
        return fail(BizResultCode.ERR, data);
    }
}
