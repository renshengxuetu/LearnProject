package com.java.learn.spring.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
/**
 *  设置 spring bean 的作用域
 *      1、singleton：默认值，当 IOC 容器一创建就会创建 bean 的实例，而且是单例，每次得到的都是同一个
 *      2、prototype：原型的，IOC 容器创建时不会创建 bean 的实例，每次调用 getBean 方法时再实例化 bean，每次都是一个新的实例
 *      3、request：每次请求实例化一个 bean
 *      4、session：在一次会话中共享一个bean
 */
@Scope("singleton")
public class Bean {


}
