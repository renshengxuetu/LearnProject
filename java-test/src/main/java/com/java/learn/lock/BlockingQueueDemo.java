package com.java.learn.lock;

import java.util.concurrent.*;

/**
 * 阻塞队列Demo
 */
public class BlockingQueueDemo {

    public static void main(String[] args){
        // 初始化一个长度为 3 的阻塞队列
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        // 多线程测试，测试结果是线程安全的
        try {
            for (int i = 0; i < 10; i++){
                final int tempIndex = i;
                new Thread(() -> {
                    System.out.println(blockingQueue.offer("t" + tempIndex));
                }).start();
            }

            for (int i = 0; i < 10; i++){
                final int tempIndex = i;
                new Thread(() -> {
                    System.out.println(blockingQueue.poll());
                }).start();
            }
            /*
            // 返回标识是否成功的插入和取出方法
            System.out.println(blockingQueue.offer("a"));
            System.out.println(blockingQueue.offer("b"));
            System.out.println(blockingQueue.offer("c"));
            System.out.println(blockingQueue.offer("d"));

            System.out.println(blockingQueue.peek());

            System.out.println(blockingQueue.poll());
            System.out.println(blockingQueue.poll());
            System.out.println(blockingQueue.poll());
            System.out.println(blockingQueue.poll());

            System.out.println(blockingQueue.peek());
            */

            /*
            // 如果无法存入或取出就会一直阻塞的插入和取出方法
            blockingQueue.put("1");
            blockingQueue.put("2");
            blockingQueue.put("3");

            blockingQueue.take();
            blockingQueue.take();
            blockingQueue.take();
            blockingQueue.take();
            */

            /*
            // 可以设置超时时间的插入和取出的方法
            System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));
            System.out.println(blockingQueue.offer("b", 2L, TimeUnit.SECONDS));
            System.out.println(blockingQueue.offer("c", 2L, TimeUnit.SECONDS));
            System.out.println(blockingQueue.offer("x", 4L, TimeUnit.SECONDS));

            System.out.println(blockingQueue.poll(2L, TimeUnit.SECONDS));
            System.out.println(blockingQueue.poll(2L, TimeUnit.SECONDS));
            System.out.println(blockingQueue.poll(2L, TimeUnit.SECONDS));
            System.out.println(blockingQueue.poll(4L, TimeUnit.SECONDS));
            */
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
