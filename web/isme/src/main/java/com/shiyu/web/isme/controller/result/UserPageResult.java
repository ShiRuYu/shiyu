package com.shiyu.web.isme.controller.result;


import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户分页数据
 */
@Data
public class UserPageResult {

    private Long id;

    private String username;

    private Boolean enable;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer gender;

    private String avatar;

    private String address;

    private String email;

    private List<RoleResult> roles;


}
