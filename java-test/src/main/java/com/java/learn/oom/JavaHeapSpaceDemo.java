package com.java.learn.oom;

/**
 * java.lang.OutOfMemoryError: Java heap space
 *  产生原因：
 *      由于对象和数组都存放在 堆 内存之中，当对象过多或者数组的长度过长时都会导致 堆 内存被撑满,
 *      从而报OutOfMemoryError: Java heap space
 */
public class JavaHeapSpaceDemo {

    public static void main(String[] args){
        byte[] bytes = new byte[80 * 1024 * 2014];
    }

}
