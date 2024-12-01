package com.shiyu.web.isme.controller.request;

import lombok.Data;

import java.util.List;

/**
 * 更新用户
 */
@Data
public class UpdateUserRequest {

    private List<Long> roleIds;

    private Boolean enable;

}
