package com.shiyu.bootstrap.isme;

import cn.hutool.crypto.digest.BCrypt;
import com.shiyu.bootstrap.isme.mapstract.IsmeRoleConvertMapper;
import com.shiyu.bootstrap.isme.mapstract.IsmeUserConvertMapper;
import com.shiyu.bootstrap.isme.request.RegisterUserRequest;
import com.shiyu.bootstrap.isme.request.UpdatePasswordRequest;
import com.shiyu.bootstrap.isme.request.UserPageRequest;
import com.shiyu.bootstrap.isme.result.UserDetailResult;
import com.shiyu.bootstrap.isme.result.UserPageResult;
import com.shiyu.commons.utils.BizResultCode;
import com.shiyu.commons.utils.ResultPage;
import com.shiyu.commons.utils.exception.BizException;
import com.shiyu.domain.auth.model.Role;
import com.shiyu.domain.auth.model.User;
import com.shiyu.domain.auth.model.UserAggregate;
import com.shiyu.domain.auth.service.AuthService;
import com.shiyu.domain.auth.service.UserService;
import com.shiyu.domain.auth.query.UserQueryCondition;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserManager {
    private final AuthService authService;
    private final UserService userService;

    public UserDetailResult detail(Long userId, String roleCode) {
        UserAggregate userAgg = authService.getUserAggregateById(userId);
        UserDetailResult userDetailResult = IsmeUserConvertMapper.INSTANCE.userAggToUserDetailResult(userAgg);
        //用户角色
        List<Role> roleList = userAgg.getRoleList();
        if (roleList.isEmpty()) {
            throw new BizException(BizResultCode.ERR_11005);
        }
        userDetailResult.setRoles(IsmeRoleConvertMapper.INSTANCE.roleListToRoleResultList(roleList));
        Role currentRole = userAgg.getCurrentRole();
        userDetailResult.setCurrentRole(IsmeRoleConvertMapper.INSTANCE.roleToRoleResult(currentRole));

        return userDetailResult;
    }

    public ResultPage<UserPageResult> findPage(UserPageRequest request) {
        UserQueryCondition condition = UserQueryCondition.builder()
                .username(request.getUsername())
                .gender(request.getGender())
                .status(request.getEnable() ? 0 : 1)
                .build();
        ResultPage<User> userResultPage = userService.selectPage(condition, request.getPageNo(), request.getPageSize());
        return IsmeUserConvertMapper.INSTANCE.userPageToPageResult(userResultPage);
    }

    public void remove(Long id) {
        userService.delete(id);
    }

    public void resetPassword(Long userId, UpdatePasswordRequest request) {
        userService.update(User.builder().id(userId).password(request.getPassword()).build());
    }

    public void create(RegisterUserRequest request) {
        boolean exists = userService.checkUserName(request.getUsername());
        if (exists) {
            throw new BizException(BizResultCode.ERR_10001);
        }
        User user = IsmeUserConvertMapper.INSTANCE.registerUserToUser(request);
        user.setPassword(BCrypt.hashpw(user.getPassword()));
        User save = userService.save(user);
        if (CollectionUtils.isNotEmpty(request.getRoleIds())) {
            authService.saveBatchUserRole(save.getId(), request.getRoleIds());
        }

    }
}
