package com.shiyu.web.isme.controller;

import com.shiyu.bootstrap.isme.MenuManager;
import com.shiyu.commons.utils.Result;
import com.shiyu.bootstrap.isme.request.CreatePermissionRequest;
import com.shiyu.bootstrap.isme.request.UpdatePermissionRequest;
import com.shiyu.bootstrap.isme.result.PermissionResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.dromara.hutool.core.tree.MapTree;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 权限Controller
 *
 */
@RestController
@RequestMapping("/permission")
@RequiredArgsConstructor
@Tag(name = "权限")
public class PermissionController {
    private final MenuManager menuManager;

    @GetMapping("menu/tree")
    @Operation(summary = "获取菜单树")
    public Result<List<MapTree<Long>>> findMenuTree() {
        List<MapTree<Long>> tree = menuManager.findAllMenuTree();
        return Result.success(tree);
    }

    @GetMapping("/tree")
    @Operation(summary = "获取所有权限树")
    public Result<List<MapTree<Long>>> findAllTree() {
        List<MapTree<Long>> tree = menuManager.findAllTree();
        return Result.success(tree);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "根据id删除")
    public Result<Void> remove(@PathVariable Long id) {
        menuManager.remove(id);
        return Result.success();
    }

    @PostMapping
    @Operation(summary = "新增权限")
    public Result<Void> create(@RequestBody @Validated CreatePermissionRequest request) {
        menuManager.create(request);
        return Result.success();
    }

    @PatchMapping("{id}")
    @Operation(summary = "修改权限")
    public Result<Void> update(@PathVariable Long id, @RequestBody UpdatePermissionRequest request) {
        menuManager.update(id, request);
        return Result.success();
    }

    @GetMapping("/button/{parentId}")
    @Operation(summary = "根据父id获取权限列表")
    public Result<List<PermissionResult>> findButtonAndApi(@PathVariable Long parentId) {
        List<PermissionResult> permissionResultList = menuManager.findButtonByPid(parentId);
        return Result.success(permissionResultList);
    }

    @PostMapping("/batch")
    @Operation(summary = "批量新增权限")
    public Result<Void> batchCreate(@RequestBody @Validated List<CreatePermissionRequest> request) {
        menuManager.batchCreate(request);
        return Result.success();
    }

    @GetMapping
    @Operation(summary = "获取所有权限")
    public Result<List<PermissionResult>> findAllMenu() {
        List<PermissionResult> permissionResultList = menuManager.findAllMenu();
        return Result.success(permissionResultList);
    }

    @GetMapping("{id}")
    @Operation(summary = "根据id获取")
    public Result<PermissionResult> findOne(@PathVariable Long id) {
        PermissionResult permissionResult = menuManager.findOne(id);
        return Result.success(permissionResult);
    }

    @GetMapping("/menu/validate")
    @Operation(summary = "校验path存不存在menu资源内")
    public Result<Boolean> validateMenuPath(String path) {
        return Result.success(menuManager.validateMenuPath(path));
    }

}
