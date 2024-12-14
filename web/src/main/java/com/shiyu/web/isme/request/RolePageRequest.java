package com.shiyu.web.isme.request;

import com.shiyu.commons.utils.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 分页
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RolePageRequest extends PageRequest {

    private String name;

    private Boolean enable;

}
