package com.shiyu.web.isme.request;

import lombok.Data;

/**
 * 更新用户信息
 *
 */
@Data
public class UpdateProfileRequest {


    private Long id;

    private Integer gender;

    private String address;

    private String email;

    private String nickName;
}
