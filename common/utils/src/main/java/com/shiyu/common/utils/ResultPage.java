package com.shiyu.common.utils;

import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class ResultPage<T> {
    /**
     * 总数
     */
    private long total = 0;

    /**
     * 查询数据列表
     */
    protected List<T> data = Collections.emptyList();

    /**
     * 响应码
     */
    private int code = 200;

    /**
     * 描述
     */
    private String message = "true";
}
