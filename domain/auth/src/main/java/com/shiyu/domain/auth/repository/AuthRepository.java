package com.shiyu.domain.auth.repository;

import java.util.List;

public interface AuthRepository {
    int saveUserRole(Long userId, Long roleId);

    int saveBatchUserRole(Long userId, List<Long> roleIdList);

    int deleteUserRole(Long userId, Long roleId);

    List<Long> selectUserRoleByUserId(Long userId);

    List<Long> selectUserRoleByRoleId(Long roleId);

    int saveRoleMenu(Long roleId, Long menuId);

    int saveBatchRoleMenu(Long roleId, List<Long> menuIdList);

    int deleteRoleMenu(Long roleId, Long menuId);

    void removeBatchRoleMenu(Long roleId, List<Long> menuIds);

    List<Long> selectRoleMenuByRoleId(Long roleId);

    List<Long> selectRoleMenuByMenuId(Long menuId);

    void removeBatchUserRole(Long roleId, List<Long> userIds);

    void saveBatchRoleUser(Long roleId, List<Long> userIds);
}
