package com.shiyu.web.aspect.preview;

import com.shiyu.commons.utils.BizResultCode;
import com.shiyu.commons.utils.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * {@link Preview}切面处理
 */
@Aspect
@Component
@ConditionalOnProperty(value = "shiyu.preview", havingValue = "true")
@Slf4j
public class PreviewAspect implements InitializingBean {


    @Override
    public void afterPropertiesSet() {
        log.info("已打开预览环境，限制部分功能.");
    }


    @Pointcut("@annotation(Preview)")
    public void pointcut() {}

    @Before("pointcut()")
    public void before() {
        throw new BizException(BizResultCode.ERR_30001);
    }


}
