package com.shiyu.web.isme.controller.request;

import lombok.Data;

import java.util.List;

/**
 * 给角色分配用户
 *
 */
@Data
public class RemoveRoleUsersRequest {

    private List<Long> userIds;

}
