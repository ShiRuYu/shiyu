package com.shiyu.infrastructure.datasource.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
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
        RolePO updatePO = RoleDBConvertMapper.INSTANCE.detailToPo(role);

        LambdaUpdateWrapper<RolePO> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(RolePO::getId, updatePO.getId());
        roleMapper.update(updatePO, updateWrapper);

        RolePO rolePO = roleMapper.selectById(role.getId());
        return RoleDBConvertMapper.INSTANCE.poToDetail(rolePO);
    }

    @Override
    public void delete(Long id) {
        roleMapper.deleteById(id);
    }

    @Override
    public ResultPage<Role> selectPage(String name, Integer status, Integer pageNo, Integer pageSize) {
        // queryWrapper组装查询where条件
        LambdaQueryWrapper<RolePO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RolePO::getName, name);
        queryWrapper.eq(RolePO::getStatus, status);
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

    @Override
    public List<Role> selectAll(Integer status) {
        QueryWrapper<RolePO> wrapper = Wrappers.query();
        wrapper.eq("status", status);
        List<RolePO> rolePOList = roleMapper.selectList(wrapper);
        return RoleDBConvertMapper.INSTANCE.listPoToDetail(rolePOList);
    }

    @Override
    public Role selectByCode(String code) {
        RolePO rolePO = roleMapper.selectOne(new LambdaQueryWrapper<RolePO>().eq(RolePO::getCode, code));
        return RoleDBConvertMapper.INSTANCE.poToDetail(rolePO);
    }

    @Override
    public Boolean checkCode(String code) {
        return roleMapper.exists(new LambdaQueryWrapper<RolePO>().eq(RolePO::getCode, code));
    }
}
