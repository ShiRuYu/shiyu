package com.shiyu.web.test;

import com.shiyu.web.test.utils.TestConstants;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class TestApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(TestApplication.class)
                .profiles(TestConstants.YML)
                .run(args);
    }

}
