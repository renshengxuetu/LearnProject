package com.java.learn.lock;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * 正计数计数线程阻塞器类
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, new LastRun());

        for (int i = 1; i <= 7; i++){
            final int tempIndex = i;
            new Thread(() -> {
                System.out.println("收集到第 " + tempIndex + " 颗龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

}

class LastRun implements Runnable{

    @Override
    public void run() {
        try{
            TimeUnit.SECONDS.sleep(2);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("1召唤神龙...");
    }

}
