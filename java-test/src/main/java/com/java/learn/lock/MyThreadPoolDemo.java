package com.java.learn.lock;

import java.util.concurrent.*;

/**
 * 第4中实现 java 多线程的方式：线程池
 *      前面三种分别是
 *          1、继承 Thread 类
 *          2、实现 Runnable 接口
 *          3、实现 Callable 接口
 */
public class MyThreadPoolDemo {

    public static void main(String[] args){
        /**
         *  四种拒绝策略
         *  (当被占用的线程数等于了最大线程数(maximumPoolSize)并且阻塞队列(workQueue)也满了的情况会调用拒绝策略拦截后续的任务)
         *      1、ThreadPoolExecutor.AbortPolicy()
         *          直接抛异常
         *      2、ThreadPoolExecutor.CallerRunsPolicy()
         *          将任务回退给调用多线程的线程
         *      3、ThreadPoolExecutor.DiscardOldestPolicy()
         *          抛弃掉等待最久的线程
         *      4、ThreadPoolExecutor.DiscardPolicy()
         *          直接丢弃线程
         *
         *  maximumPoolSize 该参数应该怎么
         *       1、CPU密集型(需要无阻塞的进行计算)
         *          maximumPoolSize = cpu 核数 + 1
         *       2、IO密集型(需要频繁得进行数据库访问等IO操作)
         *          maximumPoolSize = cpu 核数 * 2
         */
        ExecutorService threadPool = new ThreadPoolExecutor(3 ,
                5 ,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());


        try{
            for (int i = 0; i < 10; i++){
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "  办理业务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }

    }

    public static void initThreadPool(){
        //ExecutorService threadPool = Executors.newFixedThreadPool(5); //一池固定 5 个线程
        //ExecutorService threadPool = Executors.newSingleThreadExecutor();       //一池固定 1 个线程
        ExecutorService threadPool = Executors.newCachedThreadPool();           //一池固定 n 个线程

        try{
            for (int i = 0; i < 10; i++){
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "  办理业务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }

}
