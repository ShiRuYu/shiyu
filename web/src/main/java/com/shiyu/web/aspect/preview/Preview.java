package com.shiyu.web.aspect.preview;

import java.lang.annotation.*;

/**
 * 标识方法在预览环境是否关闭
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Preview {

}
