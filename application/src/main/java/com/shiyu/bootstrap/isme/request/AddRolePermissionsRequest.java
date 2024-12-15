package com.shiyu.bootstrap.isme.request;

import lombok.Data;

import java.util.List;

/**
 * 添加角色权限
 *
 */
@Data
public class AddRolePermissionsRequest {

    private Long id;

    private List<Long> permissionIds;
}
