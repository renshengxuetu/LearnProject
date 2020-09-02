package com.atguigu.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.atguigu.springcloud.dao.PaymentDao;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements PaymentService {

    public String paymentInfo_OK(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + " paymentInfo_OK, id：" + id + " OKK!!!";
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler", commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="3000")
    })
    public String paymentInfo_Timeout(Integer id) {
        int timeNum = 5;
        try{
            TimeUnit.SECONDS.sleep(timeNum);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + " paymentInfo_OK, id：" + id + " (*^_^*)  耗时(秒)：" + timeNum;
    }

    public String paymentInfo_TimeoutHandler(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + " paymentInfo /(ㄒoㄒ)/~~";
    }

    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallBack", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),                      //是否启用断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),         //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),   //时间窗口期(在多少时间周期类)
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")        //失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(Integer id){
        if(id < 0){
            throw new RuntimeException("id 不能为负数！");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t 调用成功，流水号：" + serialNumber;
    }

    public String paymentCircuitBreaker_fallBack(Integer id){
        return "id 不能为负数，请稍后再试，/(ㄒoㄒ)/~~   id：" + id;
    }


}
