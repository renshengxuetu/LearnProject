import cn.hutool.core.thread.ThreadUtil;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class ArrayListDemo {

    private static ArrayList<String> list = new ArrayList<>();

    public static void main(String[] args){

        System.out.println(-10 >>> 1);

        System.out.println(-10 >> 1);

//        for(int i = 0; i < 100; i++){
//            new Thread(() -> {
//                list.add(UUID.randomUUID().toString());
//            },"t" + i).start();
//        }
//
//        for(int j = 0; j < 100; j++){
//            new Thread(() -> {
//                list.add(UUID.randomUUID().toString());
//            },"tt" + j).start();
//        }
//
//
//        new Thread(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(2);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            list.forEach(System.out::println);
//        },"t5").start();
    }

}
