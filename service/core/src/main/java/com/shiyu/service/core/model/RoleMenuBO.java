package com.shiyu.service.core.model;

import lombok.Data;

@Data
public class RoleMenuBO {
    /**
     * ID
     */
    private Long id;
    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 菜单ID
     */
    private Long menuId;
}

