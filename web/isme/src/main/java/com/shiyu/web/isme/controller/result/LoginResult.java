package com.shiyu.web.isme.controller.result;

import lombok.Data;

/**
 * 登录响应
 */
@Data
public class LoginResult {

    /**
     * 通过登录获取的token
     */
    private String accessToken;

}
