package com.java.learn.designpattern;

import com.java.learn.designpattern.bo.IService;
import com.java.learn.designpattern.bo.Service;
import com.java.learn.designpattern.bo.ServiceInvocationHandler;

import java.lang.reflect.Proxy;

/**
 * 设计模式之动态代理
 */
public class DynamicProxy {

    public static void main(String[] args){
        IService service = new Service();
        Class<? extends IService> aClass = service.getClass();
        IService proxyService = (IService)Proxy.newProxyInstance(aClass.getClassLoader(),
                aClass.getInterfaces(), new ServiceInvocationHandler(service));
        proxyService.service();
    }

}
