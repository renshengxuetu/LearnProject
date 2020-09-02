package com.java.learn.lock;

import java.util.concurrent.CountDownLatch;

/**
 * 倒计数计数线程阻塞器类
 *  当计数器的数值清空后，await()方法后面的代码才能够被执行
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws Exception{

        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++){
            new Thread(() -> {
                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName() + " 同学离开自习室...");
            }, "t" + i).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + " 班长关闭自习室...");
    }

}
