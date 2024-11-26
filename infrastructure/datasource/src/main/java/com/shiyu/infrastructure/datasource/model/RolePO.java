package com.shiyu.infrastructure.datasource.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
@TableName("sy_role")
public class RolePO implements Serializable {

    @Serial
    private static final long serialVersionUID = -58561680146804391L;
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

