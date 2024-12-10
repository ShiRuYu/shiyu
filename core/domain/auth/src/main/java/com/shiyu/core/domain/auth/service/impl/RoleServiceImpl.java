package com.shiyu.core.domain.auth.service.impl;

import com.shiyu.commons.utils.ResultPage;
import com.shiyu.core.domain.auth.model.Role;
import com.shiyu.core.domain.auth.repository.RoleRepository;
import com.shiyu.core.domain.auth.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role update(Role role) {
        return roleRepository.update(role);
    }

    @Override
    public void delete(Long id) {
        roleRepository.delete(id);
    }

    @Override
    public ResultPage<Role> selectPage(Integer pageNo, Integer pageSize) {
        return roleRepository.selectPage(pageNo, pageSize);
    }

    @Override
    public Role selectById(Long id) {
        return roleRepository.selectById(id);
    }

    @Override
    public List<Role> selectBatchIds(List<Long> roleIdList) {
        return roleRepository.selectBatchIds(roleIdList);
    }
}
