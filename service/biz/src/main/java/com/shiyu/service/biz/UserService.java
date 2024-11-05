package com.shiyu.service.biz;

import com.shiyu.infrastructure.datasource.model.UserPO;

public interface UserService {
    UserPO save(UserPO userPO);

    UserPO findById(Long id);
}
