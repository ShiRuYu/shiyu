package com.shiyu.core.domain.auth.service;

import com.shiyu.core.domain.auth.model.RoleAggregate;
import com.shiyu.core.domain.auth.model.UserAggregate;

public interface AuthService {

    UserAggregate getUserAggregateById(Long id);

    RoleAggregate getRoleAggregateById(Long id);
}
