package com.shiyu.infrastructure.datasource.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("sy_role_menu")
public class RoleMenuPO implements Serializable {

    @Serial
    private static final long serialVersionUID = 479366146899215994L;
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
     * 角色ID
     */
    private Long roleId;
    /**
     * 菜单ID
     */
    private Long menuId;
    
    /**
     * 0：删除  1：正常  
     */
    private Integer delStatus;
}

