package com.shiyu.service.core.mapstruct;

import com.shiyu.infrastructure.datasource.model.UserPO;
import com.shiyu.service.core.mapstruct.utils.UserUtils;
import com.shiyu.service.core.model.UserBO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(imports = {UserUtils.class})
public interface UserCoreMapper {
    UserCoreMapper INSTANCE = Mappers.getMapper(UserCoreMapper.class);

    @Mapping(target = "currentRoleId", expression = "java(UserUtils.getRoleIdByExtInfo(userPO.getExtInfo()))")
    UserBO poToBO(UserPO userPO);

    UserPO boToPo(UserBO userBO);

}
