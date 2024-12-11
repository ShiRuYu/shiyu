package com.shiyu.core.domain.auth.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MenuConvertMapper {
    MenuConvertMapper INSTANCE = Mappers.getMapper(MenuConvertMapper.class);

}
