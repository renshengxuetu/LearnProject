package com.atguigu.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class FlowLimitController {

    @GetMapping(value="/testA")
    public String TestA(){
        return "---------------TestA";
    }

    @GetMapping(value="/testB")
    public String TestB(){
        log.info(Thread.currentThread().getName() + "\t  ......testB");
        return "---------------TestB";
    }

    @GetMapping(value="/testD")
    public String TestD(){
//        try{
//            TimeUnit.SECONDS.sleep(1);
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }
        int i = 1 /0;
        return "-----------TestD";
    }

    @GetMapping(value="/testE")
    public String TestE(){
        int i = 1 /0;
        return "-----------TestE";
    }

    @GetMapping(value = "/testHotKey")
    @SentinelResource(value = "testHotKey", blockHandler = "dealHandler_test")
    public String testHotKey(@RequestParam(value="p1",required = false)String p1,
                             @RequestParam(value="p2",required = false)String p2){
        return "-----------testHotKey";
    }

    public String dealHandler_test(String p1, String p2, BlockException exception){
        return "---------------dealHandler_test /(ㄒoㄒ)/~~";
    }

}
