package com.shiyu.commons.utils.cache;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.captcha.ICaptcha;
import cn.hutool.core.lang.Pair;
import cn.hutool.core.lang.UUID;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;

public class CaptchaCacheUtil {

    // 创建缓存实例
    private static final Cache<String, ICaptcha> cache = Caffeine.newBuilder()
            .maximumSize(1000)                     // 最大缓存条目数
            .expireAfterWrite(10, TimeUnit.MINUTES) // 写入后10分钟过期
            .refreshAfterWrite(5, TimeUnit.MINUTES) // 5分钟后刷新缓存
            .build();

    // 获取缓存中的值
    public static ICaptcha get(String key) {
        return cache.getIfPresent(key);
    }

    // 向缓存中添加值
    public static void put(String key, ICaptcha value) {
        cache.put(key, value);
    }

    // 移除缓存中的值
    public static void invalidate(String key) {
        cache.invalidate(key);
    }

    // 清除所有缓存
    public static void invalidateAll() {
        cache.invalidateAll();
    }

    // 获取缓存的大小
    public static long size() {
        return cache.estimatedSize();
    }

    // 示例：加载缓存值
    public static ICaptcha getOrLoad(String key) {
        return cache.get(key, CaptchaCacheUtil::loadFromDatabase); // 从数据库或其他来源加载数据
    }

    // 没有缓存赋新值
    private static ICaptcha loadFromDatabase(String key) {
        CircleCaptcha captcha = getCaptcha();
        put(key, captcha);
        return captcha;
    }

    //创建验证缓存
    public static Pair<String, ICaptcha> create() {
        CircleCaptcha captcha = getCaptcha();
        String key = UUID.randomUUID().toString(true);
        put(key, captcha);
        return Pair.of(key, captcha);
    }

    //创建新的验证码
    private static CircleCaptcha getCaptcha(){
        return CaptchaUtil.createCircleCaptcha(80, 40, 4, 4);
    }

    //验证
    public static boolean verify(String key, String code) {
        ICaptcha captcha = get(key);
        if (captcha == null) {
            return false;
        }
        invalidate(key);
        return captcha.verify(code);
    }

}
