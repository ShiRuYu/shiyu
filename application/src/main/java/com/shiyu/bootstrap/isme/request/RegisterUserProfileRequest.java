package com.shiyu.bootstrap.isme.request;

import lombok.Data;

/**
 * 注册用户的用户信息
 *
 */
@Data
public class RegisterUserProfileRequest {

    private String nickName;

    private Integer gender;

    private String avatar;

    private String address;

    private String email;


}
