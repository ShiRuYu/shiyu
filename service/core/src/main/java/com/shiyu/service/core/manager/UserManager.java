package com.shiyu.service.core.manager;

import com.shiyu.infrastructure.datasource.model.UserPO;

public interface UserManager {
    UserPO save(UserPO userPO);

    UserPO findById(Long id);
}
