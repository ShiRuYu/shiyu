package com.shiyu.web.isme.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.convert.NumberWithFormat;
import com.shiyu.bootstrap.isme.UserManager;
import com.shiyu.bootstrap.isme.request.*;
import com.shiyu.commons.utils.Result;
import com.shiyu.commons.utils.ResultPage;
import com.shiyu.commons.utils.ShiYuConstants;
import com.shiyu.commons.utils.exception.BizException;
import com.shiyu.commons.utils.BizResultCode;
import com.shiyu.bootstrap.isme.result.UserDetailResult;
import com.shiyu.bootstrap.isme.result.UserPageResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户Controller
 *
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "用户")
public class UserController {
    private final UserManager userManager;

    @GetMapping("/detail")
    @Operation(summary = "用户信息")
    public Result<UserDetailResult> detail() {
        NumberWithFormat userId =
                (NumberWithFormat) StpUtil.getExtra(ShiYuConstants.JWT_USER_ID_KEY);
        String roleCode = (String) StpUtil.getExtra(ShiYuConstants.JWT_CURRENT_ROLE_KEY);
        UserDetailResult userDetailResult = userManager.detail(userId.longValue(), roleCode);
        return Result.success(userDetailResult);
    }

    @GetMapping
    @Operation(summary = "获取所有")
    public ResultPage<UserPageResult> findPage(UserPageRequest request) {
        ResultPage<UserPageResult> userPageResultList = userManager.findPage(request);
        return userPageResultList.successThis();
    }

    @DeleteMapping("{id}")
    @Operation(summary = "根据id删除")
    public Result<Void> remove(@PathVariable Long id) {
        NumberWithFormat userId =
                (NumberWithFormat) StpUtil.getExtra(ShiYuConstants.JWT_USER_ID_KEY);
        if (userId.longValue() == id) {
            throw new BizException(BizResultCode.ERR_11006, "非法操作，不能删除自己！");
        }
        userManager.remove(id);
        return Result.success();
    }

    @PatchMapping("{id}")
    @Operation(summary = "根据id更新")
    public Result<Void> update(@PathVariable Long id, @RequestBody UpdateUserRequest request) {
        return Result.success();
    }

    @PatchMapping("password/reset/{userId}")
    @Operation(summary = "重置密码")
    public Result<Void> resetPassword(@PathVariable Long userId, @RequestBody @Validated UpdatePasswordRequest request) {
        userManager.resetPassword(userId, request);
        return Result.success();
    }

    @PostMapping
    @Operation(summary = "新增用户")
    public Result<Void> create(@RequestBody @Validated RegisterUserRequest request) {
        userManager.create(request);
        return Result.success();
    }

    @PatchMapping("/profile/{id}")
    @Operation(summary = "更新资料")
    //todo 待实现
    public Result<Void> updateProfile(@PathVariable Long id, @RequestBody UpdateProfileRequest request) {
        NumberWithFormat userId =
                (NumberWithFormat) StpUtil.getExtra(ShiYuConstants.JWT_USER_ID_KEY);
        if (userId.longValue() != id) {
            throw new BizException(BizResultCode.ERR_11004, "越权操作，用户资料只能本人修改！");
        }
        return Result.success();
    }

    @GetMapping("/{username}")
    @Operation(summary = "根据用户名获取")
    //todo 待实现
    public void findByUsername(@PathVariable String username) {
    }

    @GetMapping("/profile/{userId}")
    @Operation(summary = "查询用户的profile")
    //todo 待实现
    public void getUserProfile(@PathVariable Long userId) {
    }

    @PostMapping("/roles/add/{userId}")
    @Operation(summary = "添加角色")
    //todo 待实现
    public Result<Void> addRoles(@PathVariable Long userId, @RequestBody @Validated AddUserRolesRequest request) {
        return Result.success();
    }
}
