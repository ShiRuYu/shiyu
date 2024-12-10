package com.shiyu.core.infrastructure.datasource.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.shiyu.commons.utils.ResultPage;
import com.shiyu.core.domain.auth.model.Role;
import com.shiyu.core.domain.auth.repository.RoleRepository;
import com.shiyu.core.infrastructure.datasource.mapper.RoleMapper;
import com.shiyu.core.infrastructure.datasource.mapstruct.RoleConvertMapper;
import com.shiyu.core.infrastructure.datasource.model.RolePO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleRepositoryImpl implements RoleRepository {
    private final RoleMapper roleMapper;

    @Override
    public Role save(Role role) {
        roleMapper.insert(RoleConvertMapper.INSTANCE.detailToPo(role));
        RolePO rolePO = roleMapper.selectById(role.getId());
        return RoleConvertMapper.INSTANCE.poToDetail(rolePO);
    }

    @Override
    public Role update(Role role) {
        roleMapper.updateById(RoleConvertMapper.INSTANCE.detailToPo(role));
        RolePO rolePO = roleMapper.selectById(role.getId());
        return RoleConvertMapper.INSTANCE.poToDetail(rolePO);
    }

    @Override
    public void delete(Long id) {
        roleMapper.deleteById(id);
    }

    @Override
    public ResultPage<Role> selectPage(Integer pageNo, Integer pageSize) {
        // queryWrapper组装查询where条件
        LambdaQueryWrapper<RolePO> queryWrapper = new LambdaQueryWrapper<>();
        // 分页参数
        PageDTO<RolePO> rolePOPageDTO = roleMapper.selectPage(new PageDTO<>(pageNo, pageSize), queryWrapper);
        return RoleConvertMapper.INSTANCE.poPageToDetailPage(rolePOPageDTO);
    }

    @Override
    public Role selectById(Long id) {
        RolePO rolePO = roleMapper.selectById(id);
        return RoleConvertMapper.INSTANCE.poToDetail(rolePO);
    }

    @Override
    public List<Role> selectBatchIds(List<Long> roleIdList) {
        List<RolePO> rolePOList = roleMapper.selectBatchIds(roleIdList);
        return RoleConvertMapper.INSTANCE.listPoToDetail(rolePOList);
    }
}
