package com.shiyu.infrastructure.datasource.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
@TableName("sy_user_role")
public class UserRolePO implements Serializable {

    @Serial
    private static final long serialVersionUID = 838221028765019863L;
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
     * 用户ID
     */
    private Long userId;
    
    /**
     * 角色ID
     */
    private Long roleId;
    
    /**
     * 0：删除  1：正常  
     */
    private Integer delStatus;
}

