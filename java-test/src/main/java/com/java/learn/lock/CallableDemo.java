package com.java.learn.lock;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * 在多线程中使用 Callable 和 Runnable 的区别
 *  Callable 拥有返回值，可以更好的定位是哪个线程出现了问题
 *
 */
public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //FutureTask 实现了 Runnable 的子类 RunnableFuture， 所以该类可以放到 Thread 的构造方法中
        FutureTask<Integer> futureTask = new FutureTask(new MyThread());
        new Thread(futureTask, "AA").start();
        //调用 futureTask 的 get 方法时，如果线程没有执行完成给出返回值就会阻塞后面代码无法执行
        int result = futureTask.get();

        System.out.println(Thread.currentThread().getName() + " ***");



        System.out.println("*** result :" + result);
    }

}

class MyThread implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName() + " *** come in call");
        try{
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return 1024;
    }

}
