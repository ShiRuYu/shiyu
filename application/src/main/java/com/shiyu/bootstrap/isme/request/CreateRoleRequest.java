package com.shiyu.bootstrap.isme.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

/**
 * 创建角色
 *
 */
@Data
public class CreateRoleRequest {

    @NotBlank(message = "角色编码不能为空")
    private String code;

    @NotBlank(message = "角色名不能为空")
    private String name;

    private List<Long> permissionIds;

    private Boolean enable;

}
