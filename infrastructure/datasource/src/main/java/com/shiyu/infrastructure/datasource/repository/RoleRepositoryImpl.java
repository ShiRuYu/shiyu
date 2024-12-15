package com.shiyu.infrastructure.datasource.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.shiyu.commons.utils.ResultPage;
import com.shiyu.domain.auth.model.Role;
import com.shiyu.domain.auth.repository.RoleRepository;
import com.shiyu.infrastructure.datasource.mapper.RoleMapper;
import com.shiyu.infrastructure.datasource.mapstruct.RoleDBConvertMapper;
import com.shiyu.infrastructure.datasource.model.RolePO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleRepositoryImpl implements RoleRepository {
    private final RoleMapper roleMapper;

    @Override
    public Role save(Role role) {
        roleMapper.insert(RoleDBConvertMapper.INSTANCE.detailToPo(role));
        RolePO rolePO = roleMapper.selectById(role.getId());
        return RoleDBConvertMapper.INSTANCE.poToDetail(rolePO);
    }

    @Override
    public Role update(Role role) {
        roleMapper.updateById(RoleDBConvertMapper.INSTANCE.detailToPo(role));
        RolePO rolePO = roleMapper.selectById(role.getId());
        return RoleDBConvertMapper.INSTANCE.poToDetail(rolePO);
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
        return RoleDBConvertMapper.INSTANCE.poPageToDetailPage(rolePOPageDTO);
    }

    @Override
    public Role selectById(Long id) {
        RolePO rolePO = roleMapper.selectById(id);
        return RoleDBConvertMapper.INSTANCE.poToDetail(rolePO);
    }

    @Override
    public List<Role> selectBatchIds(List<Long> roleIdList) {
        List<RolePO> rolePOList = roleMapper.selectBatchIds(roleIdList);
        return RoleDBConvertMapper.INSTANCE.listPoToDetail(rolePOList);
    }
}
