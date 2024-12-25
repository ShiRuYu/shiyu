package com.shiyu.domain.auth.service;

import com.shiyu.domain.auth.model.RoleAggregate;
import com.shiyu.domain.auth.model.UserAggregate;

import java.util.List;

public interface AuthService {

    void saveBatchUserRole(Long id, List<Long> roleIds);

    UserAggregate getUserAggregateById(Long id);

    void saveBatchRoleMenu(Long roleId, List<Long> menuIds);

    RoleAggregate getRoleAggregateById(Long id);
}
