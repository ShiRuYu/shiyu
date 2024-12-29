package com.shiyu.web.aspect.preview;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 预览环境属性
 */
@Data
@ConfigurationProperties(prefix = "shiyu")
@Configuration
public class PreviewProperties {

    /**
     * 是否打开预览环境
     */
    private Boolean preview;

}
