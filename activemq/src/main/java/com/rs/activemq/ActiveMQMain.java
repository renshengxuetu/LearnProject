package com.rs.activemq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
// 开启定时调用
@EnableScheduling
public class ActiveMQMain {

    public static void main(String[] args){
        SpringApplication.run(ActiveMQMain.class, args);
    }

}
