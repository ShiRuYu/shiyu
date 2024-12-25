package com.shiyu.bootstrap.isme.mapstract;

import com.shiyu.bootstrap.isme.request.RegisterUserRequest;
import com.shiyu.domain.auth.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IsmeUserConvertMapper {
    IsmeUserConvertMapper INSTANCE = Mappers.getMapper(IsmeUserConvertMapper.class);

    @Mappings({
            @Mapping(source = "enable", target = "status",qualifiedByName = "booleanToInt"),
            @Mapping(source = "profile.nickName", target = "nickName"),
            @Mapping(source = "profile.gender", target = "gender"),
            @Mapping(source = "profile.avatar", target = "avatar"),
            @Mapping(source = "profile.address", target = "addr"),
            @Mapping(source = "profile.email", target = "email"),
    })
    User registerUserToUser(RegisterUserRequest registerUserRequest);

    @Named("booleanToInt")
    default long stringToLong(Boolean value) {
        return value ? 0 : 1;
    }
}
