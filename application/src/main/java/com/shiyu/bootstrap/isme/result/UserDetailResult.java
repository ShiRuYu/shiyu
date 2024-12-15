package com.shiyu.bootstrap.isme.result;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户详细信息
 */
@Data
public class UserDetailResult {

    private Long id;

    private String username;

    private Boolean enable;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private RoleResult currentRole;

    private ProfileResult profile;

    private List<RoleResult> roles;
}
