package com.shiyu.core.domain.auth.model;

import lombok.Data;

import java.util.Date;

@Data
public class Role {

    /**
     * ID
     */
    private Long id;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 角色名
     */
    private String name;

    /**
     * code
     */
    private String code;

    /**
     * code
     */
    private String permissionIds;

    /**
     * 0：正常
     */
    private Integer status;

    /**
     * 0：删除  1：正常
     */
    private Integer delStatus;
}
