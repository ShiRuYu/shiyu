package com.shiyu.domain.auth.query;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserQueryCondition {

    private String username;
    /**
     * int	性别 0：保密 1：男 2：女
     */
    private Integer gender;

    private Integer status;
}
