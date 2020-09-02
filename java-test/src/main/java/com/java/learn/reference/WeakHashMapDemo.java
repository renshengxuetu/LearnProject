package com.java.learn.reference;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * 弱引用HashMap类型
 */
public class WeakHashMapDemo {

    public static void main(String[] args){
        myHashmap();
        myWeakHashmap();
    }

    private static void myHashmap(){
        Map<Integer, String> map = new HashMap<>();
        Integer key = new Integer(1);
        String value = "HashMap";

        map.put(key, value);
        System.out.println(map);

        key = null;
        System.gc();
        System.out.println(map);
    }

    private static void myWeakHashmap(){
        Map<Integer, String> map = new WeakHashMap<>();
        Integer key = new Integer(1);
        String value = "HashMap";

        map.put(key, value);
        System.out.println(map);

        key = null;
        // WeakHashMap 只要触发gc就会被清空
        System.gc();
        System.out.println(map);
    }

}
