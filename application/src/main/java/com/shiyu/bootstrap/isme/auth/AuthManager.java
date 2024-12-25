package com.shiyu.bootstrap.isme.auth;

import cn.dev33.satoken.stp.SaLoginConfig;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.shiyu.bootstrap.isme.mapstract.IsmeUserConvertMapper;
import com.shiyu.bootstrap.isme.request.ChangePasswordRequest;
import com.shiyu.bootstrap.isme.request.LoginRequest;
import com.shiyu.bootstrap.isme.request.RegisterUserRequest;
import com.shiyu.bootstrap.isme.result.LoginResult;
import com.shiyu.commons.utils.BizResultCode;
import com.shiyu.commons.utils.ShiYuConstants;
import com.shiyu.commons.utils.exception.BizException;
import com.shiyu.domain.auth.model.Role;
import com.shiyu.domain.auth.model.User;
import com.shiyu.domain.auth.model.UserAggregate;
import com.shiyu.domain.auth.service.AuthService;
import com.shiyu.domain.auth.service.RoleService;
import com.shiyu.domain.auth.service.UserService;
import com.shiyu.infrastructure.datasource.cache.CaptchaCacheHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthManager {
    private final CaptchaCacheHelper captchaCacheHelper;
    private final AuthService authService;
    private final UserService userService;
    private final RoleService roleService;

    public LoginResult login(LoginRequest request) {
        User user = userService.selectByNameAndPasswd(request.getUsername(), request.getPassword());
        if (user == null) {
            throw new BizException(BizResultCode.ERR_10002);
        }
        //校验验证码
        if (StrUtil.isBlank(request.getCaptchaKey())
                || !captchaCacheHelper.verify(request.getCaptchaKey(), request.getCaptcha())) {
            throw new BizException(BizResultCode.ERR_10003);
        }
        //登录
        boolean checkPw = BCrypt.checkpw(request.getPassword(), user.getPassword());
        if (checkPw) {
            //用户聚合根
            UserAggregate userAgg = authService.getUserAggregateById(user.getId());
            //用户的角色
            List<Role> roleList = userAgg.getRoleList();
            //生成token
            return generateToken(userAgg, roleList.isEmpty() ? "" : roleList.get(0).getCode());
        } else {
            throw new BizException(BizResultCode.ERR_10002);
        }
    }

    private LoginResult generateToken(UserAggregate userAgg, String currentRoleCode) {
        // 密码验证成功
        StpUtil.login(userAgg.getId(),
                SaLoginConfig.setExtra(ShiYuConstants.JWT_USER_ID_KEY, userAgg.getId())
                        .setExtra(ShiYuConstants.JWT_USERNAME_KEY, userAgg.getUsername())
                        .setExtra(ShiYuConstants.JWT_CURRENT_ROLE_KEY, currentRoleCode)
                        .setExtra(ShiYuConstants.JWT_ROLE_LIST_KEY, userAgg.getRoleList()));
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        LoginResult loginResult = new LoginResult();
        loginResult.setAccessToken(tokenInfo.getTokenValue());
        return loginResult;
    }

    public LoginResult switchRole(Long userId, String roleCode) {
        //用户聚合根
        UserAggregate userAgg = authService.getUserAggregateById(userId);
        //用户的角色
        List<Role> roleList = userAgg.getRoleList();
        roleList.stream()
                .filter(role -> role.getCode().equals(roleCode))
                .findFirst()
                .orElseThrow(() -> new BizException(BizResultCode.ERR_10005));
        //生成token
        return generateToken(userAgg, roleCode);
    }

    public void register(RegisterUserRequest request) {
        boolean exists = userService.checkUserName(request.getUsername());
        if (exists) {
            throw new BizException(BizResultCode.ERR_10001);
        }
        User user = IsmeUserConvertMapper.INSTANCE.registerUserToUser(request);
        user.setPassword(BCrypt.hashpw(user.getPassword()));
        User save = userService.save(user);
        if (CollUtil.isNotEmpty(request.getRoleIds())) {
            authService.saveBatchUserRole(save.getId(), request.getRoleIds());
        }

    }

    public LoginResult refreshToken() {
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        StpUtil.login(tokenInfo.getLoginId(), SaLoginConfig
                .setExtra(ShiYuConstants.JWT_USER_ID_KEY,
                        StpUtil.getExtra(ShiYuConstants.JWT_USER_ID_KEY))
                .setExtra(ShiYuConstants.JWT_USERNAME_KEY,
                        StpUtil.getExtra(ShiYuConstants.JWT_USERNAME_KEY))
                .setExtra(ShiYuConstants.JWT_CURRENT_ROLE_KEY,
                        StpUtil.getExtra(ShiYuConstants.JWT_CURRENT_ROLE_KEY))
                .setExtra(ShiYuConstants.JWT_ROLE_LIST_KEY,
                        StpUtil.getExtra(ShiYuConstants.JWT_ROLE_LIST_KEY))
        );
        SaTokenInfo newTokenInfo = StpUtil.getTokenInfo();
        LoginResult loginResult = new LoginResult();
        loginResult.setAccessToken(newTokenInfo.getTokenValue());
        return loginResult;

    }

    public void changePassword(ChangePasswordRequest request) {
        Long userId = (Long) StpUtil.getExtra(ShiYuConstants.JWT_USER_ID_KEY);
        User user = userService.selectById(userId);
        if (!BCrypt.checkpw(request.getOldPassword(), user.getPassword())) {
            throw new BizException(BizResultCode.ERR_10004);
        }
        user.setPassword(BCrypt.hashpw(request.getNewPassword()));
        userService.update(user);
        StpUtil.logout();
    }
}
