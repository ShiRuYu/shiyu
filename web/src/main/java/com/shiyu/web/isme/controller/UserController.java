package com.shiyu.web.isme.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.convert.NumberWithFormat;
import com.google.common.collect.Lists;
import com.shiyu.commons.utils.Result;
import com.shiyu.commons.utils.ResultPage;
import com.shiyu.commons.utils.exception.BizException;
import com.shiyu.commons.utils.exception.BizResultCode;
import com.shiyu.web.config.SaTokenConfigure;
import com.shiyu.web.isme.request.*;
import com.shiyu.web.isme.result.UserDetailResult;
import com.shiyu.web.isme.result.UserPageResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户Controller
 *
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "用户")
public class UserController {

    @GetMapping("/detail")
    @Operation(summary = "用户信息")
    public Result<UserDetailResult> detail() {
        UserDetailResult userDetailResult = new UserDetailResult();
        NumberWithFormat userId =
                (NumberWithFormat) StpUtil.getExtra(SaTokenConfigure.JWT_USER_ID_KEY);
        String roleCode = (String) StpUtil.getExtra(SaTokenConfigure.JWT_CURRENT_ROLE_KEY);

        return Result.success(userDetailResult);
    }

    @GetMapping
    @Operation(summary = "获取所有")
    public ResultPage<UserPageResult> findAll(UserPageRequest request) {
        List<UserPageResult> userPageResultList = Lists.newArrayList();
        return ResultPage.success(userPageResultList);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "根据id删除")
    public Result<Void> remove(@PathVariable Long id) {
        NumberWithFormat userIdFormat = (NumberWithFormat) StpUtil.getExtra(SaTokenConfigure.JWT_USER_ID_KEY);
        if (userIdFormat.longValue() == id) {
            throw new BizException(BizResultCode.ERR_11006, "非法操作，不能删除自己！");
        }
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

        return Result.success();
    }

    @PostMapping
    @Operation(summary = "新增用户")
    public Result<Void> create(@RequestBody @Validated RegisterUserRequest request) {
        return Result.success();
    }

    @PatchMapping("/profile/{id}")
    @Operation(summary = "更新资料")
    public Result<Void> updateProfile(@PathVariable Long id, @RequestBody UpdateProfileRequest request) {
        NumberWithFormat userIdFormat = (NumberWithFormat) StpUtil.getExtra(SaTokenConfigure.JWT_USER_ID_KEY);
        if (userIdFormat.longValue() != id) {
            throw new BizException(BizResultCode.ERR_11004, "越权操作，用户资料只能本人修改！");
        }
        return Result.success();
    }

    @GetMapping("/{username}")
    @Operation(summary = "根据用户名获取")
    public void findByUsername(@PathVariable String username) {
    }

    @GetMapping("/profile/{userId}")
    @Operation(summary = "查询用户的profile")
    public void getUserProfile(@PathVariable Long userId) {
    }

    @PostMapping("/roles/add/{userId}")
    @Operation(summary = "添加角色")
    public Result<Void> addRoles(@PathVariable Long userId, @RequestBody @Validated AddUserRolesRequest request) {
        return Result.success();
    }
}
