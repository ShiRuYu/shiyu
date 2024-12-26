package com.shiyu.infrastructure.datasource.mapstruct;

import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.shiyu.commons.utils.ConvertUtil;
import com.shiyu.commons.utils.ResultPage;
import com.shiyu.domain.auth.model.User;
import com.shiyu.infrastructure.datasource.model.UserPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Map;

@Mapper(imports = ConvertUtil.class)
public interface UserDBConvertMapper {
    UserDBConvertMapper INSTANCE = Mappers.getMapper(UserDBConvertMapper.class);

    @Mapping(source = "extInfo", target = "extInfo", qualifiedByName = "strToMap")
    User poToDetail(UserPO userPO);

    List<User> listPoToDetail(List<UserPO> userPOList);

    @Mapping(source = "extInfo", target = "extInfo", qualifiedByName = "mapToStr")
    UserPO detailToPo(User user);

    @Mapping(source = "records",target = "data")
    ResultPage<User> poPageToDetailPage(PageDTO<UserPO> userPOPageDTO);

    @Named("strToMap")
    default Map<String, Object> strToMap(String extInfo) {
        return ConvertUtil.strToMap(extInfo);
    }
    @Named("mapToStr")
    default String mapToStr(Map<String, Object> extInfoMap) {
        return ConvertUtil.mapToStr(extInfoMap);
    }

}
