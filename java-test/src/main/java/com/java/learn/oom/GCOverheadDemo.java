package com.java.learn.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * java.lang.OutOfMemoryError: GC overhead limit exceeded
 * 产生原因：
 *  GC时间过长会抛出 java.lang.OutOfMemoryError: GC overhead limit exceeded ，过长的定义是超过98%的时间都用来做GC并且回收了不到
 *  2%的堆内存
 */
public class GCOverheadDemo {

    public static void main(String[] args){
        int i = 0;
        List<String> list = new ArrayList<>();
        while (true){
            list.add(String.valueOf(++i));
        }
    }

}
