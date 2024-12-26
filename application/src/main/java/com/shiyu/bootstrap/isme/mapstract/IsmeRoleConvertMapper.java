package com.shiyu.bootstrap.isme.mapstract;

import com.shiyu.bootstrap.isme.result.RoleResult;
import com.shiyu.commons.utils.ConvertUtil;
import com.shiyu.domain.auth.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(imports = {ConvertUtil.class})
public interface IsmeRoleConvertMapper {
    IsmeRoleConvertMapper INSTANCE = Mappers.getMapper(IsmeRoleConvertMapper.class);

    @Mapping(source = "status", target = "enable", qualifiedByName = "intToBoolean")
    RoleResult roleToRoleResult(Role role);

    List<RoleResult> roleListToRoleResultList(List<Role> roles);

    @Named("intToBoolean")
    default boolean intToBoolean(int value) {
        return ConvertUtil.intToBoolean(value);
    }
}
