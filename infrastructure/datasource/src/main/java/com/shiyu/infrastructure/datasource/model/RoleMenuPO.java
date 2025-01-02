package com.shiyu.infrastructure.datasource.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.shiyu.framework.mybatisplus.dataobject.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sy_role_menu")
public class RoleMenuPO extends BaseDO implements Serializable {

    @Serial
    private static final long serialVersionUID = 479366146899215994L;
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

