package com.shiyu.web.isme.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.lang.tree.Tree;
import com.google.common.collect.Lists;
import com.shiyu.common.utils.Result;
import com.shiyu.common.utils.ResultPage;
import com.shiyu.web.isme.config.SaTokenConfigure;
import com.shiyu.web.isme.result.PermissionResult;
import com.shiyu.web.isme.result.RolePageResult;
import com.shiyu.web.isme.result.RoleResult;
import com.shiyu.web.isme.request.*;
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


    @GetMapping("/page")
    @Operation(summary = "分页")
    public ResultPage<RolePageResult> findPagination(RolePageRequest request) {
        List<RolePageResult> rolePageResultList = Lists.newArrayList();
        return ResultPage.success(rolePageResultList);
    }

    @GetMapping
    @Operation(summary = "获取所有角色")
    public ResultPage<RoleResult> findAll(@RequestParam(value = "enable", required = false) Boolean enable) {
        List<RoleResult> roleResultList = Lists.newArrayList();
        return ResultPage.success(roleResultList);
    }

    @PatchMapping("{id}")
    @Operation(summary = "根据id更新")
    public Result<Void> update(@PathVariable Long id, @RequestBody UpdateRoleRequest request) {
        return Result.success();
    }

    @DeleteMapping("{id}")
    @Operation(summary = "根据id删除")
    public Result<Void> remove(@PathVariable Long id) {
        return Result.success();
    }


    @PatchMapping("/users/remove/{roleId}")
    @Operation(summary = "移除角色")
    public Result<Void> removeRoleUsers(@PathVariable Long roleId, @RequestBody RemoveRoleUsersRequest request) {
        return Result.success();
    }

    @PatchMapping("/users/add/{roleId}")
    @Operation(summary = "给角色分配用户")
    public Result<Void> addRoleUsers(@PathVariable Long roleId, @RequestBody AddRoleUsersRequest request) {
        return Result.success();
    }

    @PostMapping
    @Operation(summary = "新增角色")
    public Result<Void> create(@RequestBody @Validated CreateRoleRequest request) {
        return Result.success();
    }

    @GetMapping("/permissions/tree")
    @Operation(summary = "角色的权限树")
    public Result<List<Tree<Long>>> permissionTree() {
        String roleCode = (String) StpUtil.getExtra(SaTokenConfigure.JWT_CURRENT_ROLE_KEY);
        List<Tree<Long>> treeList = Lists.newArrayList();
        return Result.success(treeList);

    }

    @GetMapping("/permissions")
    @Operation(summary = "查询角色权限")
    public Result<List<PermissionResult>> findRolePermissions(Long id) {
        List<PermissionResult> permissionResultList = Lists.newArrayList();
        return Result.success(permissionResultList);
    }

    @GetMapping("{id}")
    @Operation(summary = "根据id获取")
    public Result<RoleResult> findOne(@PathVariable Long id) {
        RoleResult roleResult = new RoleResult();
        return Result.success(roleResult);
    }

    @PostMapping("/permissions/add")
    @Operation(summary = "给角色添加权限")
    public Result<Void> addRolePermissions(@RequestBody @Validated AddRolePermissionsRequest request) {
        return Result.success();
    }

}
