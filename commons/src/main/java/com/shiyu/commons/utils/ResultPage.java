package com.shiyu.commons.utils;

import com.shiyu.commons.utils.exception.BizResultCode;
import lombok.Data;

import java.util.Collection;
import java.util.Collections;

@Data
public class ResultPage<T> {
    /**
     * 总数
     */
    private long total = 0;

    /**
     * 查询数据列表
     */
    protected Collection<T> data = Collections.emptyList();

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


    public static <T> ResultPage<T> success(BizResultCode resultCode) {
        ResultPage<T> result = new ResultPage<>();
        result.setCode(resultCode.getCode());
        result.setMessage(resultCode.getMsg());
        result.setSuccess(true);
        return result;
    }

    public static <T> ResultPage<T> success(BizResultCode resultCode, Collection<T> data) {
        ResultPage<T> result = new ResultPage<>();
        result.setData(data);
        result.setCode(resultCode.getCode());
        result.setMessage(resultCode.getMsg());
        result.setSuccess(true);
        return result;
    }

    public static <T> ResultPage<T> fail(BizResultCode resultCode) {
        ResultPage<T> result = new ResultPage<>();
        result.setCode(resultCode.getCode());
        result.setMessage(resultCode.getMsg());
        result.setSuccess(false);
        return result;
    }

    public static <T> ResultPage<T> fail(BizResultCode resultCode, Collection<T> data) {
        ResultPage<T> result = new ResultPage<>();
        result.setData(data);
        result.setCode(resultCode.getCode());
        result.setMessage(resultCode.getMsg());
        result.setSuccess(false);
        return result;
    }

    public static <T> ResultPage<T> success() {
        return success(BizResultCode.SUC);
    }

    public static <T> ResultPage<T> success(Collection<T> data) {
        return success(BizResultCode.SUC, data);
    }

    public static <T> ResultPage<T> fail() {
        return fail(BizResultCode.ERR);
    }

    public static <T> ResultPage<T> fail(Collection<T> data) {
        return fail(BizResultCode.ERR, data);
    }
}
