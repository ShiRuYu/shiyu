package com.shiyu.domain.auth.service;

import com.shiyu.commons.utils.ResultPage;
import com.shiyu.domain.auth.model.Role;

import java.util.List;

public interface RoleService {
    Role save(Role role);

    Role update(Role role);

    void delete(Long id);

    ResultPage<Role> selectPage(Integer pageNo, Integer pageSize);

    Role selectById(Long id);

    List<Role> selectBatchIds(List<Long> roleIdList);
}
