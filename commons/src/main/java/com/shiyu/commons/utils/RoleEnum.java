package com.shiyu.commons.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * 系统默认角色，不允许修改
 */
@Getter
@AllArgsConstructor
public enum RoleEnum {
    SUPER_ADMIN("SUPER_ADMIN", "超级管理员"),
    SYS_ADMIN("SYS_ADMIN", "系统管理员"),
    ;

    private final String code;
    private final String desc;

    public static List<String> getRoleCodes() {
        return List.of(SUPER_ADMIN.getCode(), SYS_ADMIN.getCode());
    }

}
