package com.java.learn.lock;

/**
 * JVM 调优常用参数
 *  1、-Xms  初始化堆内存大小
 *  2、-Xmx  推内存最大大小
 *  3、-Xss  栈空间内存大小
 *  4、-Xmn  年轻代空间内存大小
 *  5、-XX:MetaspaceSize :设置元空间大小
 *  6、-XX:+PrintGCDetails :设置打印垃圾回收详情
 */
public class HelloGC {

    public static void main(String[] args){
        //xmsAndXmx();
        printGCDetails();
    }

    public static void printGCDetails(){
        System.out.println("恩赫");
        //byte[] bs = new byte[50 * 1024 * 1024];
    }

    public static void xmsAndXmx(){
        long l = Runtime.getRuntime().maxMemory();      //jvm 最大的堆内存大小
        long l1 = Runtime.getRuntime().totalMemory();   //jvm 初始化堆内存大小
        System.out.println("-Xms " + (l1 / 1024 / 1024) + " mb");
        System.out.println("-Xmx " + (l / 1024/ 1024) + " mb");
    }
}
