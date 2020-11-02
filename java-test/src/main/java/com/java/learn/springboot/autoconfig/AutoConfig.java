package com.java.learn.springboot.autoconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "auto.config")
public class AutoConfig {

    private static String a;

    private static String b;

    public static String getA() {
        return a;
    }

    public static void setA(String a) {
        AutoConfig.a = a;
    }

    public static String getB() {
        return b;
    }

    public static void setB(String b) {
        AutoConfig.b = b;
    }

    @Override
    public String toString(){
        return a + "---" + b;
    }

}
