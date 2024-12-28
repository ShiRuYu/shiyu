package com.shiyu.infrastructure.datasource.mapstruct;

import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.shiyu.commons.utils.ResultPage;
import com.shiyu.domain.auth.model.Role;
import com.shiyu.infrastructure.datasource.model.RolePO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RoleDBConvertMapper {
    RoleDBConvertMapper INSTANCE = Mappers.getMapper(RoleDBConvertMapper.class);

    RolePO roleToPo(Role role);

    Role poToRole(RolePO rolePO);

    List<Role> listPoToRole(List<RolePO> rolePOList);

    @Mapping(source = "records",target = "data")
    ResultPage<Role> poPageToRolePage(PageDTO<RolePO> rolePOPageDTO);
}
