package com.shiyu.infrastructure.datasource.mapstruct;

import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.shiyu.common.utils.ResultPage;
import com.shiyu.domain.auth.model.Role;
import com.shiyu.infrastructure.datasource.model.RolePO;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

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
