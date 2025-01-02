package com.shiyu.commons.utils;

import com.shiyu.commons.utils.exception.BizException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

public class AssertUtils {

    public static void isTrue(boolean expression) {
        isTrue(expression, StringUtils.EMPTY);
    }

    public static void isTrue(boolean expression, String message) {
        isTrue(expression, BizResultCode.ERR_10009,message);
    }

    public static void isTrue(boolean expression, BizResultCode resultCode) {
        isTrue(expression, BizResultCode.ERR_10009, StringUtils.EMPTY);
    }

    public static void isTrue(boolean expression, BizResultCode resultCode, String message) {
        if (!expression) {
            throw new BizException(resultCode, message);
        }
    }

    public static void isFalse(boolean expression) {
        isFalse(expression, StringUtils.EMPTY);
    }

    public static void isFalse(boolean expression, String message) {
        isFalse(expression, BizResultCode.ERR_10009,message);
    }

    public static void isFalse(boolean expression, BizResultCode resultCode) {
        isFalse(expression, BizResultCode.ERR_10009, StringUtils.EMPTY);
    }

    public static void isFalse(boolean expression, BizResultCode resultCode, String message) {
        if (expression) {
            throw new BizException(resultCode, message);
        }
    }

    public static void isNull(Object object) {
        isNull(object, StringUtils.EMPTY);
    }

    public static void isNull(Object object, String message) {
        isNull(object, BizResultCode.ERR_10009, message);
    }

    public static void isNull(Object object, BizResultCode resultCode) {
        isNull(object, resultCode, StringUtils.EMPTY);
    }

    public static void isNull(Object object, BizResultCode resultCode, String message) {
        if (Objects.nonNull(object)) {
            throw new BizException(resultCode, message);
        }
    }

    public static void nonNull(Object object) {
        nonNull(object, StringUtils.EMPTY);
    }

    public static void nonNull(Object object, String message) {
        nonNull(object, BizResultCode.ERR_10009, message);
    }

    public static void nonNull(Object object, BizResultCode resultCode) {
        nonNull(object, resultCode, StringUtils.EMPTY);
    }

    public static void nonNull(Object object, BizResultCode resultCode, String message) {
        if (Objects.isNull(object)) {
            throw new BizException(resultCode, message);
        }
    }

    public static void isNotBlank(String text) {
        isNotBlank(text, StringUtils.EMPTY);
    }

    public static void isNotBlank(String text ,String message) {
        isNotBlank(text, BizResultCode.ERR_10009, message);
    }

    public static void isNotBlank(String text, BizResultCode resultCode) {
        isNotBlank(text, resultCode, StringUtils.EMPTY);
    }

    public static void isNotBlank(String text, BizResultCode resultCode, String message) {
        if (StringUtils.isBlank(text)) {
            throw new BizException(resultCode, message);
        }
    }

    public static void isNotEmpty(Collection<?> collection) {
        isNotEmpty(collection, StringUtils.EMPTY);
    }

    public static void isNotEmpty(Collection<?> collection, String message) {
        isNotEmpty(collection, BizResultCode.ERR_10009, message);
    }

    public static void isNotEmpty(Collection<?> collection, BizResultCode resultCode) {
        isNotEmpty(collection, resultCode, StringUtils.EMPTY);
    }

    public static void isNotEmpty(Collection<?> collection, BizResultCode resultCode, String message) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new BizException(resultCode, message);
        }
    }

    public static void isNotEmpty(Map<?, ?> map) {
        isNotEmpty(map, StringUtils.EMPTY);
    }

    public static void isNotEmpty(Map<?, ?> map, String message) {
        isNotEmpty(map, BizResultCode.ERR_10009, message);
    }

    public static void isNotEmpty(Map<?, ?> map, BizResultCode resultCode) {
        isNotEmpty(map, resultCode, StringUtils.EMPTY);
    }

    public static void isNotEmpty(Map<?, ?> map, BizResultCode resultCode, String message) {
        if (MapUtils.isEmpty(map)) {
            throw new BizException(resultCode, message);
        }
    }

    public static void isNotEmpty(Object[] array) {
        isNotEmpty(array, StringUtils.EMPTY);
    }

    public static void isNotEmpty(Object[] array, String message) {
        isNotEmpty(array, BizResultCode.ERR_10009, message);
    }

    public static void isNotEmpty(Object[] array, BizResultCode resultCode) {
        isNotEmpty(array, resultCode, StringUtils.EMPTY);
    }

    public static void isNotEmpty(Object[] array, BizResultCode resultCode, String message) {
        if (ArrayUtils.isEmpty(array)) {
            throw new BizException(resultCode, message);
        }
    }
}

