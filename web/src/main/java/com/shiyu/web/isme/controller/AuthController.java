package com.shiyu.web.isme.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.convert.NumberWithFormat;
import com.shiyu.bootstrap.isme.AuthManager;
import com.shiyu.commons.utils.Result;
import com.shiyu.commons.utils.ShiYuConstants;
import com.shiyu.infrastructure.datasource.cache.CaptchaCacheHelper;
import com.shiyu.bootstrap.isme.request.ChangePasswordRequest;
import com.shiyu.bootstrap.isme.request.LoginRequest;
import com.shiyu.bootstrap.isme.request.RegisterUserRequest;
import com.shiyu.bootstrap.isme.result.LoginResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.dromara.hutool.swing.captcha.ICaptcha;
import org.springframework.web.bind.annotation.*;

/**
 * 鉴权相关的Controller.
 *
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "鉴权")
public class AuthController {
    private final AuthManager authManager;
    private final CaptchaCacheHelper captchaCacheHelper;


    @PostMapping("/login")
    @Operation(summary = "登录")
    public Result<LoginResult> login(@RequestBody final LoginRequest request,
                                     HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        String captchaKey = (String) session.getAttribute(ShiYuConstants.CAPTCHA_KEY);
        if (captchaKey != null) {
            request.setCaptchaKey(captchaKey);
        }
        LoginResult loginResult = authManager.login(request);
        return Result.success(loginResult);
    }

    @GetMapping("/captcha")
    @Operation(summary = "验证码")
    @SneakyThrows
    public void captcha(HttpServletRequest request, HttpServletResponse response){
        Pair<String, ICaptcha> captchaPair = captchaCacheHelper.create();
        HttpSession session = request.getSession();
        session.setAttribute(ShiYuConstants.CAPTCHA_KEY, captchaPair.getKey());
        captchaPair.getValue().write(response.getOutputStream());
    }

    @PostMapping("/current-role/switch/{roleCode}")
    @Operation(summary = "切换角色")
    public Result<LoginResult> switchRole(@PathVariable String roleCode) {
        NumberWithFormat userId =
                (NumberWithFormat) StpUtil.getExtra(ShiYuConstants.JWT_USER_ID_KEY);
        LoginResult loginResult = authManager.switchRole(userId.longValue(),roleCode);
        return Result.success(loginResult);
    }

    @PostMapping("/logout")
    @Operation(summary = "退出登录")
    public Result<Void> logout() {
        StpUtil.logout();
        return Result.fail();
    }


    @PostMapping("/register")
    @Operation(summary = "注册")
    public Result<Void> register(@RequestBody RegisterUserRequest request) {
        authManager.register(request);
        return Result.success();
    }

    @GetMapping("/refresh/token")
    @Operation(summary = "刷新token")
    public Result<LoginResult> refreshToken() {
        LoginResult loginResult = authManager.refreshToken();
        return Result.success(loginResult);
    }

    @PostMapping("/password")
    @Operation(summary = "修改密码")
    public Result<Void> changePassword(@RequestBody ChangePasswordRequest request) {
        authManager.changePassword(request);
        return Result.success();
    }

}
