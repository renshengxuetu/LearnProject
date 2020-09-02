package com.java.learn.oom;

import sun.misc.VM;

import java.nio.ByteBuffer;

/**
 * java.lang.OutOfMemoryError: Direct buffer memory
 * 配置JVM参数：
 *  -Xms10, -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 *
 * 导致原因：
 *  写 NIO(非阻塞式IO) 程序经常使用 ByteBuffer 来读取或写入数据，这是一种基于通道(Channel)与缓冲区(Buffer)的I/O方式
 *  它可以使用Native函数库直接分配堆外内存，然后通过一个存储在Java堆里面的 DirectByteBuffer 对象作为这块内存的引用进行操作
 *
 *  ByteBuffer.allocteDirect(capability) 这一种方式分配 OS 本地内存，不属于 GC 管辖范围
 *  但如果不断分配本地内存，堆内存很少使用，那么JVM就不需要执行 GC ，DirectByteBuffer 对象们就不会被回收
 *  这时候堆内存充足，但本地内存已经用光了，再次尝试分配本地内存就会出现 java.lang.OutOfMemoryError: Direct buffer memory
 */
public class DirectBufferMemoryDemo {

    public static void main(String[] args){
        System.out.println("配置的 MaxDirectMemory: " + (VM.maxDirectMemory() / (double) 1024 / 1024) + " MB");
        ByteBuffer bb = ByteBuffer.allocateDirect(6 * 1024 * 1024);
    }

}
