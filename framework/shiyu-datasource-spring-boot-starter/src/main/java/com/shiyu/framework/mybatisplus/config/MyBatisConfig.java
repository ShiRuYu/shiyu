package com.shiyu.framework.mybatisplus.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.shiyu.framework.mybatisplus.handler.DefaultFieldMetaHandler;
import com.shiyu.framework.mybatisplus.injector.MySqlInjector;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

@AutoConfiguration(before = MybatisPlusAutoConfiguration.class)
@MapperScan(value = "${shiyu.info.mybatis.base-package}")
public class MyBatisConfig {

    @Bean
    public MetaObjectHandler defaultMetaObjectHandler() {
        return new DefaultFieldMetaHandler(); // 自动填充参数类
    }

    @Bean
    public MySqlInjector sqlInjector() {
        return new MySqlInjector();
    }

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //添加分页插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}

