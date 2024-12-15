package com.shiyu.bootstrap.isme.auth;

import cn.dev33.satoken.stp.SaLoginConfig;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.shiyu.bootstrap.isme.request.LoginRequest;
import com.shiyu.bootstrap.isme.result.LoginResult;
import com.shiyu.commons.utils.BizResultCode;
import com.shiyu.commons.utils.ShiYuConstants;
import com.shiyu.commons.utils.exception.BizException;
import com.shiyu.infrastructure.datasource.cache.CaptchaCacheHelper;
import com.shiyu.domain.auth.model.Role;
import com.shiyu.domain.auth.model.User;
import com.shiyu.domain.auth.model.UserAggregate;
import com.shiyu.domain.auth.service.AuthService;
import com.shiyu.domain.auth.service.RoleService;
import com.shiyu.domain.auth.service.UserService;
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
        //用户聚合根
        UserAggregate userAgg = authService.getUserAggregateById(user.getId());
        //登录
        boolean checkPw = BCrypt.checkpw(request.getPassword(), user.getPassword());
        if (checkPw) {
            // 查询用户的角色
            List<Role> roleList = userAgg.getRoleList();

            return generateToken(user, roleList, roleList.isEmpty() ? "" : roleList.get(0).getCode());
        } else {
            throw new BizException(BizResultCode.ERR_10002);
        }
    }

    private LoginResult generateToken(User user, List<Role> roles, String currentRoleCode) {
        // 密码验证成功
        StpUtil.login(user.getId(),
                SaLoginConfig.setExtra(ShiYuConstants.JWT_USER_ID_KEY, user.getId())
                        .setExtra(ShiYuConstants.JWT_USERNAME_KEY, user.getUsername())
                        .setExtra(ShiYuConstants.JWT_CURRENT_ROLE_KEY, currentRoleCode)
                        .setExtra(ShiYuConstants.JWT_ROLE_LIST_KEY, roles));
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        LoginResult dto = new LoginResult();
        dto.setAccessToken(tokenInfo.getTokenValue());
        return dto;
    }
}
