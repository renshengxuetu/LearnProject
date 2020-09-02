package com.java.learn.lock;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 死锁Demo
 *
 * 排除死锁的命令
 *     idea 快捷键 Alt + F12 打开命令窗口
 *     执行 jps -l 查看java类的进程号
 *     执行 jstack 进程号    查看java进程具体的运行情况
 */
public class DeadLockDemo {

    public static void main(String[] args) {
        //lockTest();
        sycnTest();
    }

    public static void lockTest(){
        shardR s = new shardR();
        new Thread(() -> {
            s.r1();
        },"AAA").start();
        new Thread(() -> {
            s.r2();
        },"BBB").start();
    }

    public static void sycnTest(){
        String lockA = "lockA";
        String lockB = "lockB";
        FutureTask futureTask = new FutureTask(new sycnR(lockA, lockB));
        FutureTask futureTask1 = new FutureTask(new sycnR(lockB, lockA));
        new Thread(futureTask, "AAA").start();
        new Thread(futureTask1, "BBB").start();
    }
}

class sycnR implements Callable<Integer> {
    private String lockA;
    private String lockB;

    public sycnR(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public Integer call() throws Exception {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName() + "持有：" + lockA + "，期望获得 " + lockB);
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName() + "获得：" + lockB + "(*^_^*)");
            }
        }
        return 0;
    }
}

class shardR{

    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();

    public int r1(){
        lock.lock();
        try{
            c1.await();
            int result = r2();
            if(result != 0){
                c1.signal();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return 2;
    }

    public int r2(){
        lock.lock();
        try{
            c2.await();
            int result = r1();
            if(result != 0){
                c2.signal();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return 1;
    }

}
