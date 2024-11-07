package com.shiyu.service.core.manager.Impl;

import com.shiyu.infrastructure.datasource.model.RolePO;
import com.shiyu.infrastructure.datasource.repository.RoleMenuRepository;
import com.shiyu.infrastructure.datasource.repository.RoleRepository;
import com.shiyu.service.core.manager.RoleManager;
import com.shiyu.service.core.mapstruct.RoleCoreMapper;
import com.shiyu.service.core.model.RoleBO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleManagerImpl implements RoleManager {
    private final RoleRepository roleRepository;
    private final RoleMenuRepository roleMenuRepository;

    @Override
    public List<RoleBO> findByIdList(List<Long> roleIds) {
        List<RolePO> rolePOList = roleRepository.findAllById(roleIds);
        return RoleCoreMapper.INSTANCE.listPoToBO(rolePOList);
    }
}
