package com.shiyu.bootstrap.isme.mapstract;

import com.shiyu.bootstrap.isme.request.CreatePermissionRequest;
import com.shiyu.bootstrap.isme.request.UpdatePermissionRequest;
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
    @Mapping(source = "routePath", target = "path")
    PermissionResult menuToPermissionResult(Menu menu);

    List<PermissionResult> menuListToPermissionResultList(List<Menu> menu);

    @Mapping(source = "enable", target = "status", qualifiedByName = "booleanToInt")
    @Mapping(source = "show", target = "show", qualifiedByName = "booleanToInt")
    @Mapping(source = "order", target = "sort")
    @Mapping(source = "path", target = "routePath")
    Menu createPermissionRequestToMenu(CreatePermissionRequest request);

    List<Menu> createPermissionListRequestToMenuList(List<CreatePermissionRequest> request);

    @Mapping(source = "enable", target = "status", qualifiedByName = "booleanToInt")
    @Mapping(source = "show", target = "show", qualifiedByName = "booleanToInt")
    @Mapping(source = "order", target = "sort")
    @Mapping(source = "path", target = "routePath")
    Menu updatePermissionRequestToMenu(UpdatePermissionRequest request);

    @Named("intToBoolean")
    default boolean intToBoolean(int value) {
        return ConvertUtil.intToBoolean(value);
    }

    @Named("booleanToInt")
    default long booleanToInt(Boolean value) {
        return ConvertUtil.booleanToInt(value);
    }
}
