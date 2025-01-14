package com.shiyu.bootstrap.isme;

import cn.dev33.satoken.stp.SaLoginConfig;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.convert.NumberWithFormat;
import com.shiyu.bootstrap.isme.mapstract.IsmeUserConvertMapper;
import com.shiyu.bootstrap.isme.request.ChangePasswordRequest;
import com.shiyu.bootstrap.isme.request.LoginRequest;
import com.shiyu.bootstrap.isme.request.RegisterUserRequest;
import com.shiyu.bootstrap.isme.result.LoginResult;
import com.shiyu.commons.utils.AssertUtils;
import com.shiyu.commons.utils.BizResultCode;
import com.shiyu.commons.utils.ShiYuConstants;
import com.shiyu.commons.utils.exception.BizException;
import com.shiyu.domain.auth.model.Role;
import com.shiyu.domain.auth.model.User;
import com.shiyu.domain.auth.model.UserAggregate;
import com.shiyu.domain.auth.service.AuthService;
import com.shiyu.domain.auth.service.UserService;
import com.shiyu.infrastructure.datasource.cache.CaptchaCacheHelper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.dromara.hutool.crypto.digest.BCrypt;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthManager {
    private final CaptchaCacheHelper captchaCacheHelper;
    private final AuthService authService;
    private final UserService userService;

    public LoginResult login(LoginRequest request) {
        //校验验证码
        AssertUtils.isTrue(StringUtils.isBlank(request.getCaptchaKey())
                || !captchaCacheHelper.verify(request.getCaptchaKey(), request.getCaptcha()), BizResultCode.ERR_10003);
        //用户聚合根
        UserAggregate userAgg = authService.selectUserAggregateByUserName(request.getUsername());
        AssertUtils.nonNull(userAgg, BizResultCode.ERR_10002);
        //登录
        userAgg.checkPwd(request.getPassword(), userAgg.getPassword());
        //用户的角色
        List<Role> roleList = userAgg.getRoleList();
        //生成token
        return generateToken(userAgg, roleList.isEmpty() ? "" : roleList.get(0).getCode());
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
        UserAggregate userAgg = authService.selectUserAggregateById(userId);
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
        AssertUtils.isFalse(userService.checkUserName(request.getUsername()), BizResultCode.ERR_10001);

        User user = IsmeUserConvertMapper.INSTANCE.registerUserToUser(request);
        user.setPassword(BCrypt.hashpw(user.getPassword()));
        User save = userService.save(user);
        if (CollectionUtils.isNotEmpty(request.getRoleIds())) {
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
        NumberWithFormat userId =
                (NumberWithFormat) StpUtil.getExtra(ShiYuConstants.JWT_USER_ID_KEY);
        User user = userService.selectById(userId.longValue());
        if (!BCrypt.checkpw(request.getOldPassword(), user.getPassword())) {
            throw new BizException(BizResultCode.ERR_10004);
        }
        user.setPassword(BCrypt.hashpw(request.getNewPassword()));
        userService.update(user);
        StpUtil.logout();
    }
}
