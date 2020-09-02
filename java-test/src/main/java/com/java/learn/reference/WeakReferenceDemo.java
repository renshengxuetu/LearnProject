package com.java.learn.reference;

import java.lang.ref.WeakReference;

/**
 * 弱引用
 *  当一个内存地址只被唯一一个对象弱引用并且发生了gc，不管内存是否充足该内存地址都会被回收
 *  软引用和弱引用可以用在加载大量图片的场景下，加载在内存中的图片数据可以被及时清理，避免出现OOM
 */
public class WeakReferenceDemo {

    public static void main(String[] args){
        weakReference();
    }

    public static void weakReference(){
        Object obj1 = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(obj1);

        System.out.println(obj1);
        System.out.println(weakReference.get());

        obj1 = null;
        System.gc();

        System.out.println(obj1);
        System.out.println(weakReference.get());
    }

}

class S{
    private String ss;

    public S(String ss){
        this.ss = ss;
    }

    @Override
    public String toString(){
        return ss;
    }

}