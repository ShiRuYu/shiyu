package com.shiyu.web.isme.request;

import lombok.Data;

import java.util.List;

/**
 * 更新角色
 *
 */
@Data
public class UpdateRoleRequest {

    private String name;

    private Boolean enable;

    private List<Long> permissionIds;


}
