package com.java.learn.classload;

/**
 * 该 demo 展示类的加载和实例化过程中的一些细节, 以及父子类关于重写方法的一些细节
 *
 */
public class Son extends Father {

    private int i = test();

    private static int j = method();

    static{
        System.out.print("(6)");
    }

    Son(){
        System.out.print("(7)");
    }

    {
        System.out.print("(8)");
    }

    public int test() {
        System.out.print("(9)");
        return 1;
    }

    private static int method() {
        System.out.print("(10)");
        return 1;
    }

    public static void main(String[] args){
        /**
         * 代码的执行结果为：
         * (5)(1)(10)(6)(9)(3)(2)(9)(8)(7)
         * (9)(3)(2)(9)(8)(7)
         *
         * 涉及以下的知识点
         *  1、类初始化过程
         *      1)、一个类有创建实例需要先加载并初始化该类
         *      2)、一个子类初始化需要先初始化父类
         *      3)、一个类初始化就是执行<clinit>()方法
         *          <clinit>() 方法由静态类变量显示赋值代码和静态代码块组成
         *          所属 <clinit>() 的方法按代码从上到下顺序执行
         *          <clinit>() 方法只执行一次
         *      应用在代码中如下：
         *          先初始化 Son 类的父类 Father，执行 Father 类的 <clinit>() 方法，在 Father 类中属于 <clinit>() 的方法有
         *          method()(静态类变量显示赋值代码) 和 static{}(静态代码块)，method() 在上面所以先执行，所以 Father 类的
         *          初始化会打印出 (5)(1), 随后进行 Son 类的初始化打印出 (10)(6)
         *  2、类实例初始化过程
         *      1)、类实例初始化就是执行<init>()方法
         *          <init>() 方法由非静态实例变量显示赋值代码和非静态代码块，对应构造器代码组成
         *          所属 <init>() 的方法按代码从上到下顺序执行，构造器代码最后执行
         *          <init>() 方法每次实例化都执行一次
         *          子类的<init>() 方法的首行就是super()(这个一定是会有的，不写也是会执行) ，即对应父类的<init>() 方法
         *      应用在代码中如下：
         *          先实例化 Son 类的父类 Father，执行 Father 类的 <init>() 方法，在 Father 类中属于 <init>() 的方法有
         *          test()(非静态实例变量显示赋值代码)，Father(){}(构造方法) 和 {}(非静态代码块)，
         *          test() 在上面所以先执行(这块会调用子类的test(),因为通过子类调重写的方法一定会调子类已经重写过的方法)，
         *          然后执行{},最后执行 Father(){}(构造方法在<init>中最后执行)，
         *          最后 Father 实例化会打印出 (9)(3)(2), 随后进行 Son 类的实例化打印出 (9)(8)(7)
         *  3、方法的重写
         *      1)、通过子类调重写的方法一定会调子类已经重写过的方法
         *      2)、static 修饰的类方法，final 修饰的方法和 private 修饰的私有方法都不可被重写
         */
        Son son = new Son();
        System.out.println();
        Son son2 = new Son();
    }

}
