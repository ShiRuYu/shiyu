package com.shiyu.domain.auth.repository;

import java.util.List;

public interface AuthRepository {
    int saveUserRole(Long userId, Long roleId);

    int deleteUserRole(Long userId, Long roleId);

    List<Long> selectUserRoleByUserId(Long userId);

    List<Long> selectUserRoleByRoleId(Long roleId);

    int saveRoleMenu(Long roleId, Long menuId);

    int deleteRoleMenu(Long roleId, Long menuId);

    List<Long> selectRoleMenuByRoleId(Long roleId);

    List<Long> selectRoleMenuByMenuId(Long menuId);
}
