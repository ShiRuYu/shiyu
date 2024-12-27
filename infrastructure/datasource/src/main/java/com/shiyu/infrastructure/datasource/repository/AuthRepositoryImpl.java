package com.shiyu.infrastructure.datasource.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shiyu.domain.auth.repository.AuthRepository;
import com.shiyu.infrastructure.datasource.mapper.*;
import com.shiyu.infrastructure.datasource.model.RoleMenuPO;
import com.shiyu.infrastructure.datasource.model.UserRolePO;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthRepositoryImpl implements AuthRepository {
    private final UserRoleMapper userRoleMapper;
    private final RoleMenuMapper roleMenuMapper;

    @Override
    public int saveUserRole(Long userId, Long roleId) {
        UserRolePO userRolePO = new UserRolePO();
        userRolePO.setUserId(userId);
        userRolePO.setRoleId(roleId);
        return userRoleMapper.insert(userRolePO);
    }

    @Override
    public int saveBatchUserRole(Long userId, List<Long> roleIdList) {
        List<UserRolePO> userRolePOList = roleIdList.stream()
                .filter(Objects::nonNull)
                .map(roleId -> {
                    UserRolePO userRolePO = new UserRolePO();
                    userRolePO.setUserId(userId);
                    userRolePO.setRoleId(roleId);
                    return userRolePO;
                })
                .toList();
        if (CollectionUtils.isNotEmpty(userRolePOList)){
            return userRoleMapper.insertBatchSomeColumn(userRolePOList);
        }
        return 0;
    }

    @Override
    public int deleteUserRole(Long userId, Long roleId) {
        LambdaQueryWrapper<UserRolePO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(UserRolePO::getUserId, userId)
                .eq(UserRolePO::getRoleId, roleId);
        return userRoleMapper.delete(queryWrapper);
    }

    @Override
    public void removeBatchUserRole(Long roleId, List<Long> userIds) {
        LambdaQueryWrapper<UserRolePO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserRolePO::getRoleId, roleId);
        queryWrapper.in(UserRolePO::getUserId, userIds);
        userRoleMapper.delete(queryWrapper);
    }

    @Override
    public void saveBatchRoleUser(Long roleId, List<Long> userIds) {
        List<UserRolePO> userRolePOList = userIds.stream()
                .filter(Objects::nonNull)
                .map(userId -> {
                    UserRolePO userRolePO = new UserRolePO();
                    userRolePO.setUserId(userId);
                    userRolePO.setRoleId(roleId);
                    return userRolePO;
                })
                .toList();
        if (CollectionUtils.isNotEmpty(userRolePOList)){
            userRoleMapper.insertBatchSomeColumn(userRolePOList);
        }
    }

    @Override
    public List<Long> selectUserRoleByUserId(Long userId) {
        LambdaQueryWrapper<UserRolePO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(UserRolePO::getUserId, userId);
        return userRoleMapper.selectList(queryWrapper)
                .stream()
                .map(UserRolePO::getRoleId)
                .toList();
    }

    @Override
    public List<Long> selectUserRoleByRoleId(Long roleId) {
        LambdaQueryWrapper<UserRolePO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(UserRolePO::getRoleId, roleId);
        return userRoleMapper.selectList(queryWrapper)
                .stream()
                .map(UserRolePO::getUserId)
                .toList();
    }


    @Override
    public int saveRoleMenu(Long roleId, Long menuId) {
        RoleMenuPO roleMenuPO = new RoleMenuPO();
        roleMenuPO.setRoleId(roleId);
        roleMenuPO.setMenuId(menuId);
        return roleMenuMapper.insert(roleMenuPO);
    }

    @Override
    public int saveBatchRoleMenu(Long roleId, List<Long> menuIdList) {
        List<RoleMenuPO> roleMenuPOList = menuIdList.stream()
                .filter(Objects::nonNull)
                .map(menuId -> {
                    RoleMenuPO roleMenuPO = new RoleMenuPO();
                    roleMenuPO.setRoleId(roleId);
                    roleMenuPO.setMenuId(menuId);
                    return roleMenuPO;
                })
                .toList();
        if (CollectionUtils.isNotEmpty(roleMenuPOList)){
            return roleMenuMapper.insertBatchSomeColumn(roleMenuPOList);
        }
        return 0;
    }

    @Override
    public int deleteRoleMenu(Long roleId, Long menuId) {
        LambdaQueryWrapper<RoleMenuPO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(RoleMenuPO::getRoleId, roleId)
                .eq(RoleMenuPO::getMenuId, menuId);
        return roleMenuMapper.delete(queryWrapper);
    }

    @Override
    public void removeBatchRoleMenu(Long roleId, List<Long> menuIds) {
        LambdaQueryWrapper<RoleMenuPO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RoleMenuPO::getRoleId, roleId);
        queryWrapper.in(RoleMenuPO::getMenuId, menuIds);
        roleMenuMapper.delete(queryWrapper);
    }

    @Override
    public List<Long> selectRoleMenuByRoleId(Long roleId) {
        LambdaQueryWrapper<RoleMenuPO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(RoleMenuPO::getRoleId, roleId);
        return roleMenuMapper.selectList(queryWrapper)
                .stream()
                .map(RoleMenuPO::getMenuId)
                .toList();
    }

    @Override
    public List<Long> selectRoleMenuByMenuId(Long menuId) {
        LambdaQueryWrapper<RoleMenuPO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(RoleMenuPO::getMenuId, menuId);
        return roleMenuMapper.selectList(queryWrapper)
                .stream()
                .map(RoleMenuPO::getRoleId)
                .toList();
    }



}
