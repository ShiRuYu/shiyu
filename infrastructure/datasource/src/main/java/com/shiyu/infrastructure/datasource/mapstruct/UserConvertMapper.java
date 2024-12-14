package com.shiyu.infrastructure.datasource.mapstruct;

import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.shiyu.commons.utils.ResultPage;
import com.shiyu.domain.auth.model.User;
import com.shiyu.infrastructure.datasource.model.UserPO;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

public interface UserConvertMapper {
    UserConvertMapper INSTANCE = Mappers.getMapper(UserConvertMapper.class);

    @Mapping(target = "extInfo", expression = "java(UserUtils.getExtInfoMap(userPO.getExtInfo()))")
    User poToDetail(UserPO userPO);

    List<User> listPoToDetail(List<UserPO> userPOList);

    @Mapping(target = "extInfo", expression = "java(UserUtils.getExtInfoStr(user.getExtInfo()))")
    UserPO detailToPo(User user);

    @Mappings(
            @Mapping(source = "records",target = "data")
    )
    ResultPage<User> poPageToDetailPage(PageDTO<UserPO> userPOPageDTO);

}
