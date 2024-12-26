package com.shiyu.commons.utils;

import com.alibaba.fastjson2.JSON;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.Objects;

public class ConvertUtil {
    public static Map<String, Object> strToMap(String extInfo) {
        Map<String, Object> result = Maps.newHashMap();
        if (StringUtils.isNotBlank(extInfo)) {
            result.putAll(JSON.parseObject(extInfo, Map.class));
        }
        return result;
    }

    public static String mapToStr(Map<String, Object> extInfoMap) {
        if (Objects.nonNull(extInfoMap)) {
            return JSON.toJSONString(extInfoMap);
        }
        return StringUtils.EMPTY;
    }

    public static long booleanToInt(Boolean value) {
        return value ? 0 : 1;
    }

    public static boolean intToBoolean(int value) {
        return value == 0;
    }
}
