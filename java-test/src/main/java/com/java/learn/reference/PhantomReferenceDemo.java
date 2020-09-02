package com.java.learn.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * 虚引用对象
 *  PhantomReference 对象必须和引用队列(ReferenceQueue) 配合使用
 *  当PhantomReference 对象被 GC 回收之后会在引用队列(ReferenceQueue) 中存入一个空的引用对象用来通知 GC 回收之后的后续工作
 */
public class PhantomReferenceDemo {

    public static void main(String[] args){
        Object obj1 = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        PhantomReference<Object> phantomReference = new PhantomReference<>(obj1, referenceQueue);
        System.out.println(phantomReference.get());     //phantomReference 的 get 方法总是返回null
        System.out.println(referenceQueue.poll());      //从referenceQueue中取出一个对象

        System.out.println("=================");

        obj1 = null;
        System.gc();
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());      //gc前队列是空的，当gc后队列被存入了值
    }

}
