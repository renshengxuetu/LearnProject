package com.java.learn.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 题目：一个初始值为 0 的变量，两个线程对其交替操作，一个加 1 ,一个减 1 ，来 5 轮,
 *  要求在两个线程并发循环的时候保证一定是先加 1 然后再减 1 的执行顺序
 *
 * 1  线程       操作(方法)       资源类
 * 2  判断       干活             通知
 * 3  防止虚假唤醒
 */
public class ProdConsumer_TraditionDemo {

    public static void main(String[] args){
        shareData shareData = new shareData();

        new Thread(() -> {
            for (int i = 1; i <= 100; i++){
                try {
                    shareData.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "AA").start();

        new Thread(() -> {
            for (int i = 1; i <= 100; i++){
                try {
                    shareData.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "BB").start();
    }

}

class shareData{

    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws Exception{
        lock.lock();
        try{
            // 判断
            while(0 != number){
                //等待
                condition.await();
            }
            //干活
            number ++;
            System.out.println(Thread.currentThread().getName() + " " + number);
            //唤醒
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void decrement() throws Exception{
        lock.lock();
        try{
            // 判断
            while(0 == number){
                //等待
                condition.await();
            }
            //干活
            number --;
            System.out.println(Thread.currentThread().getName() + " " + number);
            //唤醒
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}
