package com.shiyu.web.isme;

import com.shiyu.web.isme.utils.IsmeConstants;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class IsmeApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(IsmeApplication.class)
                .profiles(IsmeConstants.YML)
                .run(args);
    }

}
