package com.shiyu.bootstrap.isme.mapstract;

import com.shiyu.bootstrap.isme.request.RegisterUserRequest;
import com.shiyu.bootstrap.isme.result.UserDetailResult;
import com.shiyu.bootstrap.isme.result.UserPageResult;
import com.shiyu.commons.utils.ConvertUtil;
import com.shiyu.commons.utils.ResultPage;
import com.shiyu.domain.auth.model.User;
import com.shiyu.domain.auth.model.UserAggregate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;


@Mapper(imports = {ConvertUtil.class})
public interface IsmeUserConvertMapper {
    IsmeUserConvertMapper INSTANCE = Mappers.getMapper(IsmeUserConvertMapper.class);

    @Mapping(source = "status", target = "enable", qualifiedByName = "intToBoolean")
    UserPageResult userToUserPageResult(User user);

    ResultPage<UserPageResult> userPageToPageResult(ResultPage<User> userResultPage);

    @Mappings({
            @Mapping(source = "enable", target = "status",qualifiedByName = "booleanToInt"),
            @Mapping(source = "profile.nickName", target = "nickName"),
            @Mapping(source = "profile.gender", target = "gender"),
            @Mapping(source = "profile.avatar", target = "avatar"),
            @Mapping(source = "profile.address", target = "addr"),
            @Mapping(source = "profile.email", target = "email"),
    })
    User registerUserToUser(RegisterUserRequest registerUserRequest);


    @Mappings({
            @Mapping(source = "status", target = "enable", qualifiedByName = "intToBoolean"),
            @Mapping(source = "gender", target = "profile.gender"),
            @Mapping(source = "avatar", target = "profile.avatar"),
            @Mapping(source = "addr", target = "profile.address"),
            @Mapping(source = "email", target = "profile.email"),
            @Mapping(source = "id", target = "profile.userId"),
            @Mapping(source = "nickName", target = "profile.nickName"),
    })
    UserDetailResult userAggToUserDetailResult(UserAggregate userAgg);

    @Named("intToBoolean")
    default boolean intToBoolean(int value) {
        return ConvertUtil.intToBoolean(value);
    }

    @Named("booleanToInt")
    default long booleanToInt(Boolean value) {
        return ConvertUtil.booleanToInt(value);
    }
}
