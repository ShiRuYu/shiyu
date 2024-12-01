package com.shiyu.web.isme.controller.result;

import lombok.Data;

import java.util.List;

/**
 * 角色Dto
 *
 */
@Data
public class RolePageResult {

    private Long id;

    private String code;

    private String name;

    private Boolean enable;

    private List<Long> permissionIds;

}
