package com.shiyu.infrastructure.datasource.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "sy_menu")
public class MenuPO implements Serializable {

    @Serial
    private static final long serialVersionUID = -50706392183930184L;
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
     * 类型(1:目录,2:菜单,3:按钮,4:状态)
     */
    private Integer type;
    
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
     * 描述
     */
    private String description;

    /**
     * 0:隐藏，1显示
     */
    private Integer show;

    /**
     * 排序
     */
    private Integer sort;
    
    /**
     * 0：正常  
     */
    private Integer status;
    
    /**
     * 0：删除  1：正常  
     */
    private Integer delStatus;
}

