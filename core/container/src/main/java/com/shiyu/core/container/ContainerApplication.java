package com.shiyu.core.container;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication(scanBasePackages = {"com.shiyu.core"})
@MapperScan(basePackages = "com.shiyu.core.infrastructure.datasource.mapper")
public class ContainerApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(ContainerApplication.class)
                .profiles("mysql","mybatis-plus","druid")
                .run(args);
    }

}
