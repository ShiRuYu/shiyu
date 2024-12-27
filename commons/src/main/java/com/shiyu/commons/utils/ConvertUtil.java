package com.shiyu.commons.utils;

import com.alibaba.fastjson2.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.*;
import java.util.stream.Collectors;

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

    public static int booleanToInt(Boolean value) {
        if (Objects.isNull(value)){
            return 0;
        }
        return value ? 0 : 1;
    }

    public static boolean intToBoolean(Integer value) {
        if (Objects.isNull(value)){
            return false;
        }
        return value == 0;
    }

    public static String listLongToStr(List<Long> value) {
        if (CollectionUtils.isEmpty(value)){
            return StringUtils.EMPTY;
        }
        return value.stream().map(String::valueOf).collect(Collectors.joining(","));
    }

    public static List<Long> strToListLong(String value) {
        if (StringUtils.isBlank(value)){
            return Lists.newArrayList();
        }
        String[] split = value.split(",");
        return Arrays.stream(split).map(Long::valueOf).collect(Collectors.toList());
    }

    public static Map<String,Object> objectToMap(Object value) {
        Map<String,Object> map = Maps.newHashMap();
        if (Objects.isNull(value)){
            return map;
        }
        map.putAll(JSON.parseObject(JSON.toJSONString(value), Map.class));
        return map;
    }
}
