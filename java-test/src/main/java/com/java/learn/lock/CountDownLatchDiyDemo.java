package com.java.learn.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 用 atomicInteger 和 自旋锁 实现 CountDownLatch 计数器的功能
 *  该例子失败了
 *  失败原因：
 *      多个线程下 atomicInteger 减到 0 的时候可能不是最后一个线程，这样的话当 while 循环结束后执行了 while 循环后的代码，还会有线程没有执行完毕
 */
public class CountDownLatchDiyDemo {

    private static AtomicInteger atomicInteger = new AtomicInteger(10);

    public static void main(String[] args){

        for (int i = 0; i < 10; i++){
            final int index = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " 同学离开了自习室, value : " + atomicInteger.getAndDecrement());
            }, "t" + i).start();
        }

        while(!atomicInteger.compareAndSet(0, 0)){

        }

        System.out.println(Thread.currentThread().getName() + " 班长锁了自习室..." + atomicInteger);
    }

}
