package com.shiyu.bootstrap.isme.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 修改密码请求
 *
 */
@Data
public class ChangePasswordRequest {


    @NotNull(message = "旧密码不能为空")
    private String oldPassword;

    @NotNull(message = "新密码不能为空")
    private String newPassword;

}
