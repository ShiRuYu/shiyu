package com.shiyu.core.domain.auth.mapstruct;

import com.shiyu.core.domain.auth.model.User;
import com.shiyu.core.domain.auth.model.UserAggregate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserConvertMapper {
    UserConvertMapper INSTANCE = Mappers.getMapper(UserConvertMapper.class);

    UserAggregate userToAggregate(User user);

}
