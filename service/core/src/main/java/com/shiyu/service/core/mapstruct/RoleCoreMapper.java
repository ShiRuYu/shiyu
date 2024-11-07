package com.shiyu.service.core.mapstruct;

import com.shiyu.infrastructure.datasource.model.RolePO;
import com.shiyu.service.core.model.RoleBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RoleCoreMapper {
    RoleCoreMapper INSTANCE = Mappers.getMapper(RoleCoreMapper.class);

    RoleBO poToBO(RolePO rolePO);

    List<RoleBO> listPoToBO(List<RolePO> rolePOList);
}
