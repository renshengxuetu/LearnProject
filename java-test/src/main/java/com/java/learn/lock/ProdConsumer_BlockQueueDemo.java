package com.java.learn.lock;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 阻塞队列版的生产者消费者
 *  程序的执行逻辑为当生产者生产一个，消费者消费一个，如此循环往复
 */
public class ProdConsumer_BlockQueueDemo {

    public static void main(String[] args){
        ShareResource shareResource = new ShareResource(new SynchronousQueue<>());
        new Thread(() ->{
            shareResource.myPord();
        }, "pord").start();
        new Thread(() ->{
            shareResource.myConsumer();
        }, "consumer").start();

        try{
            TimeUnit.SECONDS.sleep(5);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        shareResource.stop();
    }

}

class ShareResource{

    private volatile boolean FLAG = true;       //默认开启生产的状态
    private AtomicInteger atomicInteger = new AtomicInteger();
    private BlockingQueue<String> blockingQueue = null;

    public ShareResource(BlockingQueue<String> blockingQueue){
        this.blockingQueue = blockingQueue;
    }

    public void myPord(){
        String data = null;
        boolean retValue;

        while (FLAG){
            data = atomicInteger.incrementAndGet() + "";
            try {
                retValue = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
                if(retValue){
                    synchronized (System.out){
                        System.out.println(Thread.currentThread().getName() + "  插入队列 " + data + " 成功");
                    }
                }else{
                    synchronized (System.out){
                        System.out.println(Thread.currentThread().getName() + "  插入队列 " + data + " 失败");
                    }
                }
                try{
                    TimeUnit.MILLISECONDS.sleep(50L);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "  停止生产，FLAG=flase，生产动作结束");
    }

    public void myConsumer(){
        String result = null;
        while(FLAG){
            try {
                result = blockingQueue.poll(2L, TimeUnit.SECONDS);
                if(result == null || "".equalsIgnoreCase(result)){
                    FLAG = false;
                    System.out.println(Thread.currentThread().getName() + "  两秒钟没有取到，消费退出");
                    return;
                }
                synchronized (System.out){
                    System.out.println(Thread.currentThread().getName() + "  消费队列 " + result + "成功");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop(){
        FLAG = false;
    }

}
