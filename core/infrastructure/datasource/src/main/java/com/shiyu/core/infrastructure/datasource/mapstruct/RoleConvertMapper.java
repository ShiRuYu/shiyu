package com.shiyu.core.infrastructure.datasource.mapstruct;


import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.shiyu.commons.utils.ResultPage;
import com.shiyu.core.domain.auth.model.Role;
import com.shiyu.core.infrastructure.datasource.model.RolePO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RoleConvertMapper {
    RoleConvertMapper INSTANCE = Mappers.getMapper(RoleConvertMapper.class);

    RolePO detailToPo(Role role);

    Role poToDetail(RolePO rolePO);

    List<Role> listPoToDetail(List<RolePO> rolePOList);

    @Mappings(
            @Mapping(source = "records",target = "data")
    )
    ResultPage<Role> poPageToDetailPage(PageDTO<RolePO> rolePOPageDTO);
}
