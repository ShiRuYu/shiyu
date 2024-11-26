package com.shiyu.domain.auth.service;

import com.shiyu.domain.auth.model.RoleAggregate;
import com.shiyu.domain.auth.model.UserAggregate;

public interface AuthService {

    UserAggregate getUserAggregateById(Long id);

    RoleAggregate getRoleAggregateById(Long id);
}
