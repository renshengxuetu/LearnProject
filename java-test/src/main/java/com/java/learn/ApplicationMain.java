package com.java.learn;

import com.java.learn.spring.bean.Bean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ApplicationMain {

    public static void main(String[] args){
        ConfigurableApplicationContext context = SpringApplication.run(ApplicationMain.class, args);
    }

}
