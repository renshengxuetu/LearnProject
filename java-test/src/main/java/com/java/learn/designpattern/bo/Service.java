package com.java.learn.designpattern.bo;

public class Service implements IService {
    @Override
    public void service() {
        System.out.println("被代理对象执行相关操作");
    }
}
