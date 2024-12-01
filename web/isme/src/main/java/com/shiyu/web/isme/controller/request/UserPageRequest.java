package com.shiyu.web.isme.controller.request;

import com.shiyu.common.utils.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户分页查询
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserPageRequest extends PageRequest {

    private String username;

    private Integer gender;

    private Integer role;

    private Boolean enable;


}
