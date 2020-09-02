package com.java.learn.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 在多线程环境下保证方法的执行顺序(使用传统的加锁方法来实现)
 *  1、synchronized
 *      保证先执行 sendMSM() 然后执行 sendEmail()
 *  2、reentrantLock
 *      保证先执行 get() 然后执行 set()
 */
public class ThreadDemo {

    public static void main(String[] args){
        Phone phone = new Phone();
        new Thread(() -> {
            phone.sendMSM();
        }, "t1").start();
        new Thread(() -> {
            phone.sendMSM();
        }, "t2").start();

        try{
            TimeUnit.SECONDS.sleep(2);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        Thread t3 = new Thread(phone, "t3");
        Thread t4 = new Thread(phone, "t4");
        t3.start();
        t4.start();
    }

}

class Phone implements Runnable{

    public synchronized void sendMSM(){
        System.out.println(Thread.currentThread().getName() + ", sendMSM");
        sendEmail();
    }

    public synchronized void sendEmail(){
        System.out.println(Thread.currentThread().getName() + ", sendEmail");
    }

    Lock lock = new ReentrantLock();

    @Override
    public void run() {
        get();
    }

    public void get(){
        lock.lock();
        try{
            
            System.out.println(Thread.currentThread().getName() + ", get");
            set();
        }finally {
            lock.unlock();
        }
    }

    public void set(){
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName() + ", set");
        }finally {
            lock.unlock();
        }
    }

}
