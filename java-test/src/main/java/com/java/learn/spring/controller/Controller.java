package com.java.learn.spring.controller;

import com.java.learn.spring.bean.Bean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试 spring bean  作用域在 request 的模式下，每次不同请求的时候对象的内存地址是否相同
 */
@RestController
@RequestMapping(value="/con")
public class Controller {

    @Autowired
    private ApplicationContext applicationContext;

    @RequestMapping(value="/get", method = RequestMethod.GET)
    public String get(){
        Bean b1 = applicationContext.getBean(Bean.class);
        Bean b2 = applicationContext.getBean(Bean.class);
        return b1 + "," + b2;
    }

}
