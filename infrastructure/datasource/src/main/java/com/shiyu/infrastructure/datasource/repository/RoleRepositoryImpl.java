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
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RoleRepositoryImpl implements RoleRepository {
    private final RoleMapper roleMapper;

    @Override
    public Role save(Role role) {
        roleMapper.insert(RoleDBConvertMapper.INSTANCE.roleToPo(role));
        RolePO rolePO = roleMapper.selectById(role.getId());
        return RoleDBConvertMapper.INSTANCE.poToRole(rolePO);
    }

    @Override
    public Role update(Role role) {
        RolePO updatePO = RoleDBConvertMapper.INSTANCE.roleToPo(role);

        LambdaUpdateWrapper<RolePO> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(RolePO::getId, updatePO.getId());
        roleMapper.update(updatePO, updateWrapper);

        RolePO rolePO = roleMapper.selectById(role.getId());
        return RoleDBConvertMapper.INSTANCE.poToRole(rolePO);
    }

    @Override
    public void delete(Long id) {
        roleMapper.deleteById(id);
    }

    @Override
    public ResultPage<Role> selectPage(String name, Integer status, Integer pageNo, Integer pageSize) {
        // queryWrapper组装查询where条件
        LambdaQueryWrapper<RolePO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(name), RolePO::getName, name);
        queryWrapper.eq(Objects.nonNull(status), RolePO::getStatus, status);
        // 分页参数
        PageDTO<RolePO> rolePOPageDTO = roleMapper.selectPage(new PageDTO<>(pageNo, pageSize), queryWrapper);
        return RoleDBConvertMapper.INSTANCE.poPageToRolePage(rolePOPageDTO);
    }

    @Override
    public Role selectById(Long id) {
        RolePO rolePO = roleMapper.selectById(id);
        return RoleDBConvertMapper.INSTANCE.poToRole(rolePO);
    }

    @Override
    public List<Role> selectBatchIds(List<Long> roleIdList) {
        List<RolePO> rolePOList = roleMapper.selectBatchIds(roleIdList);
        return RoleDBConvertMapper.INSTANCE.listPoToRole(rolePOList);
    }

    @Override
    public List<Role> selectAll(Integer status) {
        QueryWrapper<RolePO> wrapper = Wrappers.query();
        wrapper.eq("status", status);
        List<RolePO> rolePOList = roleMapper.selectList(wrapper);
        return RoleDBConvertMapper.INSTANCE.listPoToRole(rolePOList);
    }

    @Override
    public Role selectByCode(String code) {
        RolePO rolePO = roleMapper.selectOne(new LambdaQueryWrapper<RolePO>().eq(RolePO::getCode, code));
        return RoleDBConvertMapper.INSTANCE.poToRole(rolePO);
    }

    @Override
    public Boolean checkCode(String code) {
        return roleMapper.exists(new LambdaQueryWrapper<RolePO>().eq(RolePO::getCode, code));
    }
}
