package com.java.learn.lock;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 可伸缩计数线程阻塞器类
 */
public class SemaphoreDemo {

    public static void main(String[] args){
        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i <= 6; i++){
            new Thread(() -> {
                try{
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + " 号车子抢到车位");
                    try{
                        TimeUnit.SECONDS.sleep(3);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " 号车子停了 3 秒钟离开了车位...");
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            }, "t" + i).start();
        }

    }

}
