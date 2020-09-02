package com.java.learn.oom;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * java.lang.OutOfMemoryError: Metaspace
 * 产生原因
 *  加载了过多的类、常量变量、静态变量
 * 永久代(java8后被原空间Metaspace取代了) 存放了一下信息:
 *  虚拟机加载的类信息
 *  常量池
 *  静态变量
 *  即时编译后的代码
 * 设置 JVM 参数
 *  -XX:MetaspaceSize=8m -XX:MaxMetaspaceSize=8m
 *
 */
public class MetaspaceOOMDemo {

    static class OOMTest{ }

    public static void main(String[] args){
        int i = 0;
        try{
            while (true){
                i++;
                Enhancer enhancer = new Enhancer();     //spring 的 cglib 的动态字节码创建类
                enhancer.setSuperclass(OOMTest.class);  //设置父类
                enhancer.setUseCache(false);            //不使用缓存
                enhancer.setCallback(new MethodInterceptor() {      //设置回调函数
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        return methodProxy.invokeSuper(o, args);
                    }
                });
                enhancer.create();
            }
        }catch (Throwable e){//Throwable 是 Exception 和 Error 的父类，OutOfMemoryError 属于错误(Error) 的子类
            System.out.println("****** 多少次后发生了错误: " + i);
            e.printStackTrace();
        }finally {

        }
    }

}
