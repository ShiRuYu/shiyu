package com.shiyu.domain.auth.service.impl;

import com.shiyu.commons.utils.ResultPage;
import com.shiyu.domain.auth.model.Role;
import com.shiyu.domain.auth.repository.RoleRepository;
import com.shiyu.domain.auth.service.RoleService;
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
    public ResultPage<Role> selectPage(String name, Integer status, Integer pageNo, Integer pageSize) {
        return roleRepository.selectPage(name, status, pageNo, pageSize);
    }

    @Override
    public Role selectById(Long id) {
        return roleRepository.selectById(id);
    }

    @Override
    public List<Role> selectBatchIds(List<Long> roleIdList) {
        return roleRepository.selectBatchIds(roleIdList);
    }

    @Override
    public List<Role> selectAll(Integer status) {
        return roleRepository.selectAll(status);
    }

    @Override
    public Role selectByCode(String code) {
        return roleRepository.selectByCode(code);
    }

    @Override
    public Boolean checkCode(String code) {
        return roleRepository.checkCode(code);
    }
}
