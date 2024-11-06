package com.shiyu.service.core.manager.Impl;

import com.shiyu.infrastructure.datasource.repository.RoleMenuRepository;
import com.shiyu.infrastructure.datasource.repository.RoleRepository;
import com.shiyu.service.core.manager.RoleManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleManagerImpl implements RoleManager {
    private final RoleRepository roleRepository;
    private final RoleMenuRepository roleMenuRepository;
}
