package com.shiyu.core.infrastructure.datasource.repository;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shiyu.core.domain.auth.repository.AuthRepository;
import com.shiyu.core.infrastructure.datasource.mapper.RoleMenuMapper;
import com.shiyu.core.infrastructure.datasource.mapper.UserRoleMapper;
import com.shiyu.core.infrastructure.datasource.model.RoleMenuPO;
import com.shiyu.core.infrastructure.datasource.model.UserRolePO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public int deleteUserRole(Long userId, Long roleId) {
        LambdaQueryWrapper<UserRolePO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(UserRolePO::getUserId, userId)
                .eq(UserRolePO::getRoleId, roleId);
        return userRoleMapper.delete(queryWrapper);
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
    public int deleteRoleMenu(Long roleId, Long menuId) {
        LambdaQueryWrapper<RoleMenuPO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(RoleMenuPO::getRoleId, roleId)
                .eq(RoleMenuPO::getMenuId, menuId);
        return roleMenuMapper.delete(queryWrapper);
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
