package com.java.learn.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁demo
 */
public class SpinLockDemo {

    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock(){
        Thread thread = Thread.currentThread();
        System.out.println("come in " + thread.getName() + ",(*^_^*)");
        while(!atomicReference.compareAndSet(null, thread)){
            System.out.print(thread.getName() + " ,wait....  ");
        }
    }

    public void myUnLock(){
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(thread.getName() + "invoked myUnLock");
    }

    public static void main(String[] args){
        SpinLockDemo spinLockDemo = new SpinLockDemo();
        new Thread(() -> {
            spinLockDemo.myLock();
            try{
                TimeUnit.SECONDS.sleep(3);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            spinLockDemo.myUnLock();
        }, "AA").start();

        try{
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        new Thread(() -> {
            spinLockDemo.myLock();
            spinLockDemo.myUnLock();
        }, "BB").start();
    }

}
