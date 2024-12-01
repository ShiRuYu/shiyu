package com.shiyu.web.isme.controller.result;

import lombok.Data;

/**
 * 用户信息
 */
@Data
public class ProfileResult {

    private Long id;

    private Integer gender;

    private String avatar;

    private String address;

    private String email;

    private Long userId;

    private String nickName;

}
