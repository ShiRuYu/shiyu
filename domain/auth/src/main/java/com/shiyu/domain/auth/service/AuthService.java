package com.shiyu.domain.auth.service;

import com.shiyu.domain.auth.model.Menu;
import com.shiyu.domain.auth.model.Role;
import com.shiyu.domain.auth.model.RoleAggregate;
import com.shiyu.domain.auth.model.UserAggregate;

import java.util.List;

public interface AuthService {

    void saveBatchUserRole(Long id, List<Long> roleIds);

    void saveBatchRoleUser(Long roleId, List<Long> userIds);

    UserAggregate selectUserAggregateById(Long id);

    UserAggregate selectUserAggregateByUserName(String username);

    void saveBatchRoleMenu(Long roleId, List<Long> menuIds);

    RoleAggregate selectRoleAggregateById(Long id);

    void removeBatchUserRole(Long roleId, List<Long> userIds);

    void removeBatchRoleMenu(Long roleId, List<Long> menuIds);

    List<Menu> selectMenuByRoleId(Long roleId);

    List<Role> selectRoleByUserId(Long userId);

    List<Long> selectMenuIdByRoleId(Long roleId);

    void removeMenuByRole(Long roleId);

    void removeRoleByUser(Long userId);
}
