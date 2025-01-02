package com.shiyu.infrastructure.datasource.cache;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.captcha.ICaptcha;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.shiyu.commons.utils.cache.CacheHelper;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class CaptchaCacheHelper extends CacheHelper<String,ICaptcha> implements InitializingBean {

    @Override
    public Cache<String, ICaptcha> createCache() {
        return Caffeine.newBuilder()
                .maximumSize(1000)                     // 最大缓存条目数
                .expireAfterWrite(10, TimeUnit.MINUTES) // 写入后10分钟过期
                .build();
    }

    @Override
    public ICaptcha defaultLoad(String key) {
        return getCaptcha();
    }

    //创建缓存
    @Override
    public Pair<String, ICaptcha> create() {
        CircleCaptcha captcha = getCaptcha();
        String key = UUID.randomUUID().toString();
        put(key, captcha);
        return Pair.of(key, captcha);
    }

    //创建新的验证码
    private CircleCaptcha getCaptcha(){
        return CaptchaUtil.createCircleCaptcha(80, 40, 4, 4);
    }

    //验证
    public boolean verify(String key, String code) {
        ICaptcha captcha = get(key);
        if (captcha == null) {
            return false;
        }
        invalidate(key);
        return captcha.verify(code);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        this.cache = createCache();
    }
}
