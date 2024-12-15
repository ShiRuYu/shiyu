package com.shiyu.domain.auth.mapstruct;


import com.shiyu.domain.auth.model.User;
import com.shiyu.domain.auth.model.UserAggregate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserConvertMapper {
    UserConvertMapper INSTANCE = Mappers.getMapper(UserConvertMapper.class);

    UserAggregate userToAggregate(User user);

}
