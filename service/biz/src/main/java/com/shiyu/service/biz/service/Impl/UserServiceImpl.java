package com.shiyu.service.biz.service.Impl;

import com.shiyu.infrastructure.datasource.model.UserPO;
import com.shiyu.service.biz.service.UserService;
import com.shiyu.service.core.manager.UserManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserManager userManager;

    @Override
    public UserPO save(UserPO userPO){
        return userManager.save(userPO);
    }
    @Override
    public UserPO findById(Long id){
        return userManager.findById(id);
    }
}
