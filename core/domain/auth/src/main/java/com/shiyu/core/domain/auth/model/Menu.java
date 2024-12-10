package com.shiyu.core.domain.auth.model;

import lombok.Data;

import java.util.Date;


@Data
public class Menu {
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
     * 上级id，根节点：0
     */
    private Long parentId;

    /**
     * 菜单名
     */
    private String name;

    /**
     * MENU/BUTTON
     */
    private String type;

    /**
     * 路径
     */
    private String uri;

    /**
     * code
     */
    private String code;

    /**
     * 图标
     */
    private String icon;

    /**
     * 路由
     */
    private String routePath;

    /**
     * /src... 地址
     */
    private String component;

    /**
     * 所属布局
     */
    private String layout;

    /**
     * 方法类型 get/post
     */
    private String method;

    /**
     * redirect
     */
    private String redirect;

    /**
     * keepAlive
     */
    private Boolean keepAlive;

    /**
     * 描述
     */
    private String description;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 0：显示 1:隐藏
     */
    private Integer status;

    /**
     * 0：删除  1：正常
     */
    private Integer delStatus;
}

