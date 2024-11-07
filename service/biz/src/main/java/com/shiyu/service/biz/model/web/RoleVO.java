package com.shiyu.service.biz.model.web;

import lombok.Data;

@Data
public class RoleVO {
    /**
     * ID
     */
    private Long id;
    /**
     * 角色名
     */
    private String name;

    /**
     * code
     */
    private String code;
}
