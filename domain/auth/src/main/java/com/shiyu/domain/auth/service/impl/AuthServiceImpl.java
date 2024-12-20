package com.shiyu.domain.auth.service.impl;

import com.shiyu.domain.auth.mapstruct.RoleConvertMapper;
import com.shiyu.domain.auth.mapstruct.UserConvertMapper;
import com.shiyu.domain.auth.model.*;
import com.shiyu.domain.auth.repository.AuthRepository;
import com.shiyu.domain.auth.repository.MenuRepository;
import com.shiyu.domain.auth.repository.RoleRepository;
import com.shiyu.domain.auth.repository.UserRepository;
import com.shiyu.domain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthRepository authRepository;
    private final MenuRepository menuRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Override
    public UserAggregate getUserAggregateById(Long id) {
        User user = userRepository.selectById(id);
        if (Objects.isNull(user)){
            return null;
        }
        UserAggregate userAggregate = UserConvertMapper.INSTANCE.userToAggregate(user);
        List<Role> roles = roleRepository.selectBatchIds(authRepository.selectUserRoleByUserId(id));
        userAggregate.setRoleList(roles);
        return userAggregate;
    }

    @Override
    public RoleAggregate getRoleAggregateById(Long id) {
        Role role = roleRepository.selectById(id);
        if (Objects.isNull(role)){
            return null;
        }
        RoleAggregate roleAggregate = RoleConvertMapper.INSTANCE.roleToAggregate(role);
        List<Menu> menus = menuRepository.selectBatchIds(authRepository.selectRoleMenuByRoleId(id));
        roleAggregate.setMenuList(menus);
        return roleAggregate;
    }
}
