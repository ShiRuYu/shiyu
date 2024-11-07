package com.shiyu.service.core.model;

import lombok.Data;

@Data
public class UserRolePO {
    /**
     * ID
     */
    private Long id;
    /**
     * 用户ID
     */
    private Long userId;
    
    /**
     * 角色ID
     */
    private Long roleId;
}

