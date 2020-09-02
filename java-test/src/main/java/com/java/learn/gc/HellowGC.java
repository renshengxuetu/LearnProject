package com.java.learn.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * 该 demo 测试几种垃圾收集器的组合使用方式
 *  1、-Xms1m -Xmx1m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseSerialGC
 *  2、-Xms1m -Xmx1m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParNewGC -XX:+UseConcMarkSweepGC
 *  3、-Xms1m -Xmx1m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelGC
 *  4、-Xms1m -Xmx1m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseG1GC
 */
public class HellowGC {

    static{

    }
    {
        System.out.println();
    }

    public static void main(String[] args){
        List<String> list = new ArrayList<>();
        int i = 0;
        while(true){
            i ++;
            list.add(i + "");
        }
    }

}
