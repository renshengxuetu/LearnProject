package com.java.learn.reference;

/**
 * 强引用对象不会被gc回收
 */
public class StrongReferenceDemo {

    public static void main(String[] args){
        Object obj1 = new Object();//这样定义的默认就是强引用
        Object obj2 = obj1;        //obj2引用赋值
        obj1 = null;
        System.gc();
        System.out.println(obj2);   //执行 gc 之后obj2依然会有值
    }

}
