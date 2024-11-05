package com.shiyu.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.shiyu"})
@EntityScan(basePackages = {"com.shiyu.infrastructure.datasource.model"})
@EnableJpaRepositories(basePackages = {"com.shiyu.infrastructure.datasource.repository"})
public class BootstrapApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootstrapApplication.class, args);
    }

}
