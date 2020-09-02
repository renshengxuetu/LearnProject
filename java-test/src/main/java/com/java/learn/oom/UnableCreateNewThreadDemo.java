package com.java.learn.oom;

import java.util.concurrent.TimeUnit;

/**
 * java.lang.OutOfMemoryError: unable to create new native thread
 *
 * 高并发请求服务器时，经常出现如下异常 java.lang.OutOfMemoryError: unable to create new native thread
 * 准确的将该 native thread 异常与对应的平台有关
 *
 * 导致原因
 *  1、应用创建了太多线程，一个应用进程创建多个线程超过了系统承载极限
 *  2、服务器不允许应用创建这么多的线程，linux 系统默认允许单个进程可以创建的线程数是1024个
 *
 *  解决方法
 *   1、更改应用逻辑代码，让应用不创建那么多的线程
 *   2、在 linux 下，通过修改 /etc/security/limits.d/ 路径下的 conf 文件，扩大 linux 默认允许的线程数
 */
public class UnableCreateNewThreadDemo {

    /**
     * 该例子不能在 windows 笔记本上跑，会死机
     * @param args
     */
    public static void main(String[] args){
        for (int i = 0; ; i++){
            try{
                System.out.println("i: " + i);
                new Thread(() -> {
                    try{
                        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }, i + "").start();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
