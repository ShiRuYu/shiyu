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
@TableName("sy_role")
public class RolePO extends BaseDO implements Serializable {

    @Serial
    private static final long serialVersionUID = -58561680146804391L;
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
    
    /**
     * 0：正常  
     */
    private Integer status = 0;
}

