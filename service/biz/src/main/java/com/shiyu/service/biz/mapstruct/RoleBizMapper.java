package com.shiyu.service.biz.mapstruct;

import com.shiyu.service.biz.model.web.RoleVO;
import com.shiyu.service.core.model.RoleBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper
public interface RoleBizMapper {
    RoleBizMapper INSTANCE = Mappers.getMapper(RoleBizMapper.class);

    RoleVO boToPO(RoleBO roleBO);

    List<RoleVO> listBOToVO(List<RoleBO> roleBOList);
}
