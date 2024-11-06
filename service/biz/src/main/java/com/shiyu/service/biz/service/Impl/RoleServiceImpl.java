package com.shiyu.service.biz.service.Impl;

import com.shiyu.service.biz.service.RoleService;
import com.shiyu.service.core.manager.RoleManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleManager roleManager;
}
