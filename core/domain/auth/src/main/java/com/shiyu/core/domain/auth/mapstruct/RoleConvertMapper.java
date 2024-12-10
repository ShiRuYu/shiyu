package com.shiyu.core.domain.auth.mapstruct;


import com.shiyu.core.domain.auth.model.Role;
import com.shiyu.core.domain.auth.model.RoleAggregate;
import org.mapstruct.factory.Mappers;

public interface RoleConvertMapper {
    RoleConvertMapper INSTANCE = Mappers.getMapper(RoleConvertMapper.class);

    RoleAggregate roleToAggregate(Role role);
}
