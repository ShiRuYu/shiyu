package com.shiyu.bootstrap.isme.mapstract;

import com.shiyu.bootstrap.isme.request.CreateRoleRequest;
import com.shiyu.bootstrap.isme.result.RolePageResult;
import com.shiyu.bootstrap.isme.result.RoleResult;
import com.shiyu.bootstrap.isme.result.UserPageResult;
import com.shiyu.commons.utils.ConvertUtil;
import com.shiyu.commons.utils.ResultPage;
import com.shiyu.domain.auth.model.Role;
import com.shiyu.domain.auth.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(imports = {ConvertUtil.class})
public interface IsmeRoleConvertMapper {
    IsmeRoleConvertMapper INSTANCE = Mappers.getMapper(IsmeRoleConvertMapper.class);

    @Mapping(source = "status", target = "enable", qualifiedByName = "intToBoolean")
    @Mapping(source = "permissionIds", target = "permissionIds", qualifiedByName = "strToListLong")
    RolePageResult roleToRolePageResult(Role role);

    ResultPage<RolePageResult> rolePageToPageResult(ResultPage<Role> roleResultPage);

    @Mapping(source = "status", target = "enable", qualifiedByName = "intToBoolean")
    RoleResult roleToRoleResult(Role role);

    List<RoleResult> roleListToRoleResultList(List<Role> roles);

    @Mapping(source = "enable", target = "status", qualifiedByName = "booleanToInt")
    @Mapping(source = "permissionIds", target = "permissionIds", qualifiedByName = "listLongToStr")
    Role createRoleRequestToRole(CreateRoleRequest request);


    @Named("intToBoolean")
    default boolean intToBoolean(int value) {
        return ConvertUtil.intToBoolean(value);
    }

    @Named("booleanToInt")
    default long booleanToInt(Boolean value) {
        return ConvertUtil.booleanToInt(value);
    }
    @Named("listLongToStr")
    default String listLongToStr(List<Long> value) {
        return ConvertUtil.listLongToStr(value);
    }
    @Named("strToListLong")
    default List<Long> strToListLong(String value) {
        return ConvertUtil.strToListLong(value);
    }
}
