package com.shiyu.service.biz.mapstruct;

import com.shiyu.infrastructure.utils.DateUtil;
import com.shiyu.service.biz.model.web.UserDTO;
import com.shiyu.service.biz.model.web.UserVO;
import com.shiyu.service.core.model.UserBO;
import lombok.SneakyThrows;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Objects;

@Mapper
public interface UserBizMapper {
    UserBizMapper INSTANCE = Mappers.getMapper(UserBizMapper.class);

    @Mapping(target = "birthday", source = "birthday")
    UserBO dtoToBO(UserDTO userDTO);
    UserVO boToVO(UserBO userBO);

    //MapStruct会将所有匹配到的：
    //源类型为Date、目标类型为String的属性，
    //按以下方法进行转换
    static String date2String(Date date) {
        if (Objects.isNull(date)){
            date = new Date();
        }
        return DateUtil.dateToStr(date);
    }
    @SneakyThrows
    static Date strin2Date(String date) {
        if (!StringUtils.hasText(date)){
            return new Date();
        }
        return DateUtil.strToDate(date);
    }
}
