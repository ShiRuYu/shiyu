package com.shiyu.bootstrap.isme.request;

import lombok.Data;

import java.util.List;

/**
 * 给用户分配角色
 */
@Data
public class AddUserRolesRequest {

    private List<Long> roleIds;

}