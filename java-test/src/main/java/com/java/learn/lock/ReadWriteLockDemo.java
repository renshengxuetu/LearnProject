package com.java.learn.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁demo
 */
public class ReadWriteLockDemo {

     public static void main(String[] args){
         MyCache myCache = new MyCache();
         for(int i = 0; i < 4; i++){
             final int index = i;
             new Thread(() -> {
                 try{
                     TimeUnit.SECONDS.sleep(1);
                 }catch (InterruptedException e){
                     e.printStackTrace();
                 }
                 myCache.put(String.valueOf(index), String.valueOf(index));
             },"w" + i).start();
             new Thread(() -> {
                 myCache.get(String.valueOf(index));
             },"r" + i).start();
         }
     }

}

class MyCache{

    private volatile Map<String, Object> map = new HashMap<>();

    private static ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void put(String key, String value){
        try{
            rwLock.writeLock().lock();
            System.out.println(Thread.currentThread().getName() + ": 正在写入, key: " + key);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + ": 写入完成");
        }catch (Exception e){
            throw e;
        }finally {
            rwLock.writeLock().unlock();
        }
    }

    public void get(String key){
        try{
            rwLock.readLock().lock();
            System.out.println(Thread.currentThread().getName() + ": 正在读取");
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName() + ": 读取完成, result:" + result);
        }catch (Exception e){
            throw e;
        }finally {
            rwLock.readLock().unlock();
        }
    }

}
