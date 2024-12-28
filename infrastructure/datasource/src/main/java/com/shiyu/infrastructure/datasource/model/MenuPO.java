package com.shiyu.infrastructure.datasource.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("sy_menu")
public class MenuPO implements Serializable {

    @Serial
    private static final long serialVersionUID = -50706392183930184L;
    /**
     * ID
     */
    private Long id;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
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
     * 排序
     */
    private Integer sort;

    /**
     * 是否展示在页面菜单 0：显示 1:隐藏
     * show 为关键字，指定生成时使用 `` 包围
     */
    @TableField("`show`")
    private Integer show = 0;

    /**
     * 0：可用 1:不可用
     */
    private Integer status = 0;
    
    /**
     * 0：删除  1：正常  
     */
    private Integer delStatus = 1;
}

