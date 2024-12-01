package com.shiyu.common.utils;

import lombok.Data;

/**
 * 分页请求
 */
@Data
public class PageRequest {

    /**
     * 页数
     */
    private Integer pageNo = 1;

    /**
     * 页大小
     */
    private Integer pageSize = 10;

}
