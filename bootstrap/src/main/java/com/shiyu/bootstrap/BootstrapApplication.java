package com.shiyu.bootstrap;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.shiyu"})
@MapperScan(basePackages = "com.shiyu.infrastructure.datasource.mapper")
public class BootstrapApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootstrapApplication.class, args);
    }

}
