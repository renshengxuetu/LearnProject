package com.java.learn.oom;

/**
 *  java.lang.StackOverflowError:栈溢出错误
 *      出现原因:
 *          jvm 中一个线程执行一个方法的时候会在栈空间中压入一个栈帧，当这个方法中又调用了其他方法的时候就会继续压入一个栈帧，
 *          但是这个栈空间中栈帧的深度是有限的，当深度超过 jvm 规定的深度的时候就会报 StackOverflowError
 */
public class StackOverFlowErrorDemo {

    private static int count;

    public static void main(String[] args){
        stackOverFlowError();
    }

    private static void stackOverFlowError() {
        System.out.println(count++);
        stackOverFlowError();
    }

}
