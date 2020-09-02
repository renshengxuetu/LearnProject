package com.java.learn.lock;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 该类和 BlockingQueue 的另外两个实现类 ArrayBlockingQueue 和 LinkedBlockingQueue 有些区别，需要单独的例子演示
 */
public class SynchronousQueueDemo {

    public static void main(String[] args){
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " put 1");
                blockingQueue.put("1");
                // 当 blockingQueue put 了一个元素之后，在这个元素没有被 take 拿走之前，它后面的代码都会被阻塞
                System.out.println(Thread.currentThread().getName() + " put 2");
                blockingQueue.put("2");
                System.out.println(Thread.currentThread().getName() + " put 3");
                blockingQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "AAA").start();

        new Thread(() -> {
            try {
                try{
                    TimeUnit.SECONDS.sleep(3);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " take " + blockingQueue.take());
                try{
                    TimeUnit.SECONDS.sleep(3);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " take " + blockingQueue.take());
                try{
                    TimeUnit.SECONDS.sleep(3);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " take " + blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "BBB").start();
    }

}
