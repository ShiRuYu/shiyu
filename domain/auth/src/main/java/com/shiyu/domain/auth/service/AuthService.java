package com.shiyu.domain.auth.service;

import com.shiyu.domain.auth.model.Menu;
import com.shiyu.domain.auth.model.RoleAggregate;
import com.shiyu.domain.auth.model.UserAggregate;

import java.util.List;

public interface AuthService {

    void saveBatchUserRole(Long id, List<Long> roleIds);

    void saveBatchRoleUser(Long roleId, List<Long> userIds);

    UserAggregate getUserAggregateById(Long id);

    void saveBatchRoleMenu(Long roleId, List<Long> menuIds);

    RoleAggregate getRoleAggregateById(Long id);

    void removeBatchUserRole(Long roleId, List<Long> userIds);

    void removeBatchRoleMenu(Long roleId, List<Long> menuIds);

    List<Menu> selectMenuByRoleId(Long roleId);
}
