package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping(value = "/consumer/payment/hystrix/paymentInfo_OK/{id}")
    public String paymentInfo_OK(@PathVariable Integer id) {
        return paymentHystrixService.paymentInfo_OK(id);
    }

    @GetMapping(value = "/consumer/payment/hystrix/paymentInfo_Timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod", commandProperties = {
//            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="1500")
//    })
    @HystrixCommand
    public String paymentInfo_Timeout(@PathVariable Integer id){
        int i = 10 / 0;
        return paymentHystrixService.paymentInfo_Timeout(id);
    }

    public String paymentTimeOutFallbackMethod(@PathVariable Integer id){
        return "系统异常，请稍后重试！";
    }

    public String payment_Global_FallbackMethod(){
        return "全局异常处理信息，请稍后重试！";
    }

}
