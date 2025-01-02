package com.shiyu.infrastructure.datasource.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.shiyu.framework.mybatisplus.dataobject.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sy_user_role")
public class UserRolePO extends BaseDO implements Serializable {

    @Serial
    private static final long serialVersionUID = 838221028765019863L;
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

