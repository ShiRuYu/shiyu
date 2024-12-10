package com.shiyu.bootstrap;

import com.shiyu.core.container.ContainerApplication;
import com.shiyu.web.isme.IsmeApplication;
import com.shiyu.web.test.TestApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;

public class BootstrapApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                //父容器
                .parent(ContainerApplication.class)
                .profiles("mysql","mybatis-plus","druid")
                //子容器
                .child(IsmeApplication.class)
                .profiles("web-isme")
                .web(WebApplicationType.SERVLET)
                //子容器的兄弟容器
                .sibling(TestApplication.class)
                .profiles("web-test")
                .web(WebApplicationType.SERVLET)
                .run(args);
    }

}
