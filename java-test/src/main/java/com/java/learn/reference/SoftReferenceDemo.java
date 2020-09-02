package com.java.learn.reference;

import java.lang.ref.SoftReference;

/**
 * 软引用对象
 *  当一个内存地址只被唯一一个对象软引用并且发生了gc，判断内存是否充足，如果不充足该内存地址就会被回收，反之不会被回收
 */
public class SoftReferenceDemo {

    public static void main(String[] args){
        //softRef_Memory_Enough();
        softRef_Memory_NoEnough();
    }

    /**
     * 内存充足的情况下
     */
    public static void softRef_Memory_Enough(){
        Object obj1 = new Object();
        SoftReference<Object> reference = new SoftReference<>(obj1);

        System.out.println(obj1);
        System.out.println(reference.get());

        obj1 = null;
        System.gc();

        System.out.println(obj1);
        System.out.println(reference.get());

    }

    /**
     * 内存不充足的情况下
     */
    public static void softRef_Memory_NoEnough(){
        Object obj1 = new Object();
        SoftReference<Object> reference = new SoftReference<>(obj1);

        System.out.println(obj1);
        System.out.println(reference.get());

        obj1 = null;

        try{
            byte[] bs = new byte[50 * 1024 * 1024];
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println(obj1);
            System.out.println(reference.get());
        }
    }

}
