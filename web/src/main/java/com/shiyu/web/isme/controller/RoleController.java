package com.shiyu.web.isme.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.lang.tree.Tree;
import com.google.common.collect.Lists;
import com.shiyu.bootstrap.isme.UserManager;
import com.shiyu.bootstrap.isme.request.*;
import com.shiyu.bootstrap.isme.result.RoleManager;
import com.shiyu.commons.utils.*;
import com.shiyu.bootstrap.isme.result.PermissionResult;
import com.shiyu.bootstrap.isme.result.RolePageResult;
import com.shiyu.bootstrap.isme.result.RoleResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色Controller
 */
@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
@Tag(name = "角色")
public class RoleController {
    private final RoleManager roleManager;


    @GetMapping("/page")
    @Operation(summary = "分页")
    public ResultPage<RolePageResult> findPage(RolePageRequest request) {
        ResultPage<RolePageResult> rolePageResultList = roleManager.findPage(request);
        return rolePageResultList.successThis();
    }

    @GetMapping
    @Operation(summary = "获取所有角色")
    public Result<List<RoleResult>> findAll(@RequestParam(value = "enable", required = true) Boolean enable) {
        List<RoleResult> roleResultList = roleManager.findAll(enable);
        return Result.success(roleResultList);
    }

    @PatchMapping("{id}")
    @Operation(summary = "根据id更新")
    public Result<Void> update(@PathVariable Long id, @RequestBody UpdateRoleRequest request) {
        roleManager.updateRole(id, request);
        return Result.success();
    }

    @DeleteMapping("{id}")
    @Operation(summary = "根据id删除")
    public Result<Void> remove(@PathVariable Long id) {
        roleManager.remove(id);
        return Result.success();
    }


    @PatchMapping("/users/remove/{roleId}")
    @Operation(summary = "移除角色")
    public Result<Void> removeRoleUsers(@PathVariable Long roleId, @RequestBody RemoveRoleUsersRequest request) {
        roleManager.removeRoleUsers(roleId, request);
        return Result.success();
    }

    @PatchMapping("/users/add/{roleId}")
    @Operation(summary = "给角色分配用户")
    public Result<Void> addRoleUsers(@PathVariable Long roleId, @RequestBody AddRoleUsersRequest request) {
        roleManager.addRoleUsers(roleId, request);
        return Result.success();
    }

    @PostMapping
    @Operation(summary = "新增角色")
    public Result<Void> create(@RequestBody @Validated CreateRoleRequest request) {
        roleManager.createRole(request);
        return Result.success();
    }

    @GetMapping("/permissions/tree")
    @Operation(summary = "角色的权限树,TOKEN中获取CODE")
    public Result<List<Tree<Long>>> permissionTree() {
        //String roleCode = (String) StpUtil.getExtra(ShiYuConstants.JWT_CURRENT_ROLE_KEY);
        List<Tree<Long>> treeList = roleManager.findRoleMenuTree(RoleEnum.SUPER_ADMIN.getCode());
        return Result.success(treeList);

    }

    @GetMapping("/permissions")
    @Operation(summary = "查询角色权限")
    public Result<List<PermissionResult>> findRolePermissions(Long id) {
        List<PermissionResult> permissionResultList = roleManager.findRoleMenu(id);
        return Result.success(permissionResultList);
    }

    @GetMapping("{id}")
    @Operation(summary = "根据id获取")
    public Result<RoleResult> findOne(@PathVariable Long id) {
        RoleResult roleResult = roleManager.findOne(id);
        return Result.success(roleResult);
    }

    @PostMapping("/permissions/add")
    @Operation(summary = "给角色添加权限")
    public Result<Void> addRolePermissions(@RequestBody @Validated AddRolePermissionsRequest request) {
        roleManager.addRolePermissions(request.getId(), request.getPermissionIds());
        return Result.success();
    }

}
