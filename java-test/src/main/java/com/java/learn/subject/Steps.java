package com.java.learn.subject;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目：有 n 步台阶，一次只能上 1 步 或 2 步，共有多少种走法
 *  该 demo 使用纯粹的代码逻辑实现打印出所有步骤的具体走法
 */
public class Steps {

    private static List<Integer> list = new ArrayList<>();
    private static List<String> strList = new ArrayList<>();
    private static Integer newNextValue = -1;
    private static boolean flag = true;
    private static List<String> resultList = new ArrayList<>();

    public static void main(String[] args){
        int n = 20;
        Integer index = 0;
        Integer nextValue = -1;
        for(int i = 1; i < n; i++){//循环几组
            nextValue = -1;
            initList(i);
            if(i == (n - 1)){
                strList.add(listToString(list));
            }else{
                strList.add(listToString(list));
                for(int j = list.size() - 1; j >= 0; j--) {
                    Integer v = list.get(j);
                    while (true){
                        if(nextValue == -1){    //表明在处理集合的最后一位
                            if((v + 1) < n){
                                list.set(j, (v + 1));
                                strList.add(listToString(list));
                                v++;
                            }else if((v + 1) == n){
                                list.set(j, (v + 1));
                                strList.add(listToString(list));
                                int k = relaodList(list, j, strList, n);
                                if(flag){
                                    nextValue = newNextValue;
                                    j = k;
                                    break;
                                }
                                nextValue = (v + 1);
                                v++;
                                break;
                            }
                        }else{                  //表明已经往前开始处理了
                            if((v + 1) < (nextValue - 1)){
                                list.set(j, (v + 1));
                                strList.add(listToString(list));
                                v++;
                            }else if((v + 1) == (nextValue - 1)){
                                list.set(j, (v + 1));
                                strList.add(listToString(list));
                                int k = relaodList(list, j, strList, n);
                                if(flag){
                                    j = k;
                                    nextValue = newNextValue;
                                    break;
                                }
                                nextValue = (v + 1);
                                v++;
                                break;
                            }else if(v == nextValue - 1){
                                strList.add(listToString(list));
                                int k = relaodList(list, j, strList, n);
                                if(flag){
                                    j = k;
                                    nextValue = newNextValue;
                                    break;
                                }
                                nextValue = v;
                                break;
                            }
                        }
                    }
                }
            }
        }
        List<Integer> as = new ArrayList<>();
        for(int c = 0; c < n; c++){
            as.add(1);
        }
        for(String str: strList){
            //System.out.println(str);
            String[] ss = str.split(",");
            for(int d = 0; d < ss.length; d++){
                as.set(Integer.valueOf(ss[d])  - 1, 2);
            }
            printResult(n, as);
            as.set(0, 2);
            printResult(n, as);
            czList(as);
        }

        czList(as);
        printResult(n, as);
        as.set(0, 2);
        printResult(n, as);

        for(String s: resultList){
            System.out.println(s.substring(0, s.length() - 1));
        }
        System.out.println("在只能走 1 步或者走 2 步的情况下，要走 " + n + " 级台阶总共有 " + resultList.size() + " 种走法！！");
    }

    public static void czList(List<Integer> list){
        for(int i = 0; i < list.size(); i++){
            list.set(i, 1);
        }
    }

    public static void printResult(int n, List<Integer> list){
        int sum = 0;
        String result = "";
        for(int i = (list.size() - 1); i >= 0; i-- ){
            sum = 0;
            result = "";
            for(int j = 0; j <= i; j ++){
                sum = sum + list.get(j);
                result += list.get(j) + ",";
            }
            if(sum == n){
                boolean ffff = false;
                for (String s: resultList) {
                    if(result.equals(s)){
                        ffff = true;
                    }
                }
                if(!ffff){
                    resultList.add(result);
                }
            }
        }
    }

    public static int relaodList(List<Integer> list, int j, List<String> strList, int n){
        flag = false;
        if((j - 1) >= 0){
            Integer v = list.get(j - 1) + 1;
            list.set(j - 1, v);
            for (int i = j; i < list.size(); i++){
                if(list.get(i) != (v + 1)){
                    flag = true;
                }
                list.set(i, ++v);
            }
        }
        if(flag){// 说明进行了数据整理
            strList.add(listToString(list));
            for(int a = (list.size() - 1); a >= 0; a-- ){
                Integer b = n - (list.size() - (a + 1));
                if(list.get(a) != b){
                    if(a == (list.size() - 1)){
                        newNextValue = -1;
                    }else{
                        newNextValue = b + 1;
                    }
                    return a + 1;
                }
            }
        }
        return j;
    }

    public static void initList(Integer groupNum){
        list.clear();
        for (int i = 1; i <= groupNum; i++){
            list.add(i + 1);
        }
    }

    public static String listToString(List list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i < list.size() - 1) {
                sb.append(',');
            }
        }
        return sb.toString();
    }

}

