package com.shiyu.service.core.manager;

import com.shiyu.infrastructure.datasource.model.UserPO;
import com.shiyu.service.core.model.UserBO;

public interface UserManager {
    UserBO save(UserBO userBO);

    UserBO findById(Long id);
}
