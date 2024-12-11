package com.shiyu.bootstrap;

import com.shiyu.core.container.ContainerApplication;
import com.shiyu.core.container.utils.CoreContainerConstants;
import com.shiyu.web.isme.IsmeApplication;
import com.shiyu.web.isme.utils.IsmeConstants;
import com.shiyu.web.test.TestApplication;
import com.shiyu.web.test.utils.TestConstants;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;

public class BootstrapApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                //父容器
                .parent(ContainerApplication.class)
                .profiles(CoreContainerConstants.CONTAINER_YML, CoreContainerConstants.DRUID_YML,
                        CoreContainerConstants.MYSQL_YML, CoreContainerConstants.MYBATIS_PLUS_YML)
                //子容器
                .child(IsmeApplication.class)
                .profiles(IsmeConstants.YML)
                .web(WebApplicationType.SERVLET)
                //子容器的兄弟容器
                .sibling(TestApplication.class)
                .profiles(TestConstants.YML)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }

}
