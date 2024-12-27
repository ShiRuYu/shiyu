package com.shiyu.web.config;

import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import com.google.common.collect.Lists;
import com.shiyu.commons.utils.ShiYuConstants;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 鉴权
 *
 */
//@Component
public class StpInterfaceImpl implements StpInterface {

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return null;
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        String role = (String) StpUtil.getExtra(ShiYuConstants.JWT_CURRENT_ROLE_KEY);
        return Lists.newArrayList(role);
    }
}
