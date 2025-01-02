package com.shiyu.domain.auth.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleAggregate {

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
    private Integer status;

    /**
     * 0：删除  1：正常
     */
    private Integer delStatus;

    /**
     * 拥有菜单
     */
    private List<Menu> menuList;
}

