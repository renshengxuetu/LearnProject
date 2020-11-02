package com.java.learn.springboot.autoconfig;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * 演示springboot的自动配置功能
 */
@EnableConfigurationProperties(AutoConfig.class)
@ConditionalOnWebApplication(
        type = ConditionalOnWebApplication.Type.SERVLET
)
public class AutoConfigDemo {

    public AutoConfigDemo(AutoConfig autoConfig){
        System.out.println("1111111" + autoConfig);
    }

}
