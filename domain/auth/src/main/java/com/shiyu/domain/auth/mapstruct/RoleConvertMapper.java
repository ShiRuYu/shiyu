package com.shiyu.domain.auth.mapstruct;

import com.shiyu.domain.auth.model.Role;
import com.shiyu.domain.auth.model.RoleAggregate;
import org.mapstruct.factory.Mappers;

public interface RoleConvertMapper {
    RoleConvertMapper INSTANCE = Mappers.getMapper(RoleConvertMapper.class);

    RoleAggregate roleToAggregate(Role role);
}
