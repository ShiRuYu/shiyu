package com.shiyu.bootstrap.isme.mapstract;

import com.shiyu.bootstrap.isme.result.PermissionResult;
import com.shiyu.commons.utils.ConvertUtil;
import com.shiyu.domain.auth.model.Menu;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(imports = {ConvertUtil.class})
public interface IsmeMenuConvertMapper {
    IsmeMenuConvertMapper INSTANCE = Mappers.getMapper(IsmeMenuConvertMapper.class);

    @Mapping(source = "status", target = "enable", qualifiedByName = "intToBoolean")
    @Mapping(source = "show", target = "show", qualifiedByName = "intToBoolean")
    @Mapping(source = "sort", target = "order")
    PermissionResult menuToPermissionResult(Menu menu);

    List<PermissionResult> menuListToPermissionResultList(List<Menu> menu);

    @Named("intToBoolean")
    default boolean intToBoolean(int value) {
        return ConvertUtil.intToBoolean(value);
    }

    @Named("booleanToInt")
    default long booleanToInt(Boolean value) {
        return ConvertUtil.booleanToInt(value);
    }
}
