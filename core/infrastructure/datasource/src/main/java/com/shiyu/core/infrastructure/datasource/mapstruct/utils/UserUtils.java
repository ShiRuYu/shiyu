package com.shiyu.core.infrastructure.datasource.mapstruct.utils;

import com.alibaba.fastjson2.JSON;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.Objects;

public class UserUtils {
    public static Map<String,Object> getExtInfoMap(String extInfo){
        if (StringUtils.isNotBlank(extInfo)){
            return JSON.parseObject(extInfo, Map.class);
        }
        return Maps.newHashMap();
    }
    public static String getExtInfoStr(Map<String,Object> extInfoMap){
        if (Objects.nonNull(extInfoMap)){
            return JSON.toJSONString(extInfoMap);
        }
        return StringUtils.EMPTY;
    }
}
