package com.shiyu.infrastructure.datasource.mapstruct;

import com.shiyu.domain.auth.model.Role;
import com.shiyu.infrastructure.datasource.model.RolePO;
import org.mapstruct.factory.Mappers;

import java.util.List;

public interface RoleConvertMapper {
    RoleConvertMapper INSTANCE = Mappers.getMapper(RoleConvertMapper.class);

    RolePO detailToPo(Role role);

    Role poToDetail(RolePO rolePO);

    List<Role> listPoToDetail(List<RolePO> rolePOList);
}
