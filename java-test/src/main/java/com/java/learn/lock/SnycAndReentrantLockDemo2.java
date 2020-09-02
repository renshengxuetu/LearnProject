package com.java.learn.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * synchronized 和 Lock 的区别
 * 1、构成上不同
 *      synchronized 是关键字，是 jvm 层面的
 *          底层是通过 monitor 对象的 monitorenter (进入方法) 方法 和 monitorexit (退出方法) 方法实现的
 *      Lock 是具体的接口，是api 层面的
 * 2、使用方法不同
 *      被 synchronized 修饰的代码快会自己释放锁，不会造成死锁的情况
 *      Lock 需要手动获取锁和释放锁，如果没有正确的释放锁就会造成死锁的情况
 * 3、是否是非公平锁
 *      synchronized 是非公平锁
 *      Lock 可以通过初始化时传入不同的参数来设置成非公平锁和公平锁
 * 4、等待是否可以中断
 *      synchronized 不可以中断
 *      Lock 可以中断
 *          1、设置超时方法 tryLock(long timeout, TimeUnit nuit)
 * 5、是否可以精确唤醒
 *      synchronized 不可以精确唤醒，只可以要么随机唤醒一个线程，要么全部唤醒
 *      Lock 可以通过 Condition 对象来进行精确唤醒
 */
public class SnycAndReentrantLockDemo2 {

    public static void main(String[] args){
        ShareData2 shareData2 = new ShareData2();
        for (int i = 0; i < 10; i++){
            new Thread(() -> {
                shareData2.print5();
            },"A").start();

            new Thread(() -> {
                shareData2.print10();
            },"B").start();

            new Thread(() -> {
                shareData2.print15();
            },"C").start();
        }
    }

}

class ShareData2{

    private int name = 1; // 1: A , 2: B , 3: C

    private static Lock lock = new ReentrantLock();
    private static Condition condition1 = lock.newCondition();
    private static Condition condition2 = lock.newCondition();
    private static Condition condition3 = lock.newCondition();

    public void print5(){
        lock.lock();
        try{
            while (name != 1){
                condition1.await();
            }
            for (int i = 0; i < 5; i++){
                System.out.println(Thread.currentThread().getName() + "   " + (i + 1));
            }
            name = 2;
            condition2.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void print10(){
        lock.lock();
        try{
            while (name != 2){
                condition2.await();
            }
            for (int i = 0; i < 10; i++){
                System.out.println(Thread.currentThread().getName() + "   " + (i + 1));
            }
            name = 3;
            condition3.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void print15(){
        lock.lock();
        try{
            while (name != 3){
                condition3.await();
            }
            for (int i = 0; i < 15; i++){
                System.out.println(Thread.currentThread().getName() + "   " + (i + 1));
            }
            name = 1;
            condition1.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}