package com.java.learn.subject;

/**
 * 题目：有 n 步台阶，一次只能上 1 步 或 2 步，共有多少种走法
 *  该 demo 使用 循环迭代 的方法实现打印出步数的总数量
 *  迭代的优缺点
 *      优点：执行效率高
 *      缺点：代码可读性较差
 */
public class Steps2 {

    public static void main(String[] args){
        System.out.println(loop(20));
    }

    /**
     * 该方法的核心逻辑基于下面的理论
     *  n = 1 时走法只有 1 种(1), 也就是 f(1) = 1
     *  n = 2 时走法有 2 中(2, 1、1)，也就是 f(2) = 2
     *  n = 3 时可以分成下面两种情况
     *      1、最后一步固定跨 1 步，也就是说前面还有 2 阶台阶，也就是 f(2) = 2
     *      2、最后一步固定跨 2 步，也就是说前面还有 1 阶台阶，也即是 f(1) = 1
     *      最后 f(3) = f(2) + f(1)
     *  n = 4 时同样可以分成下面两种情况
     *      1、最后一步固定跨 1 步，也就是说前面还有 3 阶台阶，也就是 f(3) = f(2) + f(1)
     *      2、最后一步固定跨 2 步，也就是说前面还有 2 阶台阶，也即是 f(2) = 2
     *      最后 f(4) = f(3) + f(2)
     *  综上得出的公式为： f(n) = f(n - 1) + f(n - 2)
     * @param n
     * @return
     */
    public static int loop(int n){
        if(n == 1 || n == 2){
            return n;
        }
        int one = 1;    //初始化 n = 1 时的总步数
        int two = 2;    //初始化 n = 2 时的总步数
        int sum = 0;    //初始化总步数
        for (int i = 3; i <= n; i++){      //循环从 3 开始，因为 1 和 2已经被直接返回了
            sum = one + two;        //获得 f(n-1) + f(n - 2) 的和
            one = two;              //将 f(n - 1) 值赋给 f(n - 2)
            two = sum;              //将 f(n-1) + f(n - 2) 的和赋给 f(n - 1)
        }
        return sum;
    }

}
