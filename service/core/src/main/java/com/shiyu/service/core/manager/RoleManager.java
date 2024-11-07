package com.shiyu.service.core.manager;

import com.shiyu.service.core.model.RoleBO;

import java.util.List;

public interface RoleManager {
    List<RoleBO> findByIdList(List<Long> roleIds);
}
