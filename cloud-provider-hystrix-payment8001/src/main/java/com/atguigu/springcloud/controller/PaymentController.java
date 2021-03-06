package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/payment/hystrix/paymentInfo_OK/{id}")
    public String paymentInfo_OK(@PathVariable Integer id) {
        return paymentService.paymentInfo_OK(id);
    }

    @GetMapping(value = "/payment/hystrix/paymentInfo_Timeout/{id}")
    public String paymentInfo_Timeout(@PathVariable Integer id){
        return paymentService.paymentInfo_Timeout(id);
    }

    @GetMapping(value = "/payment/hystrix/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id")Integer id){
        return paymentService.paymentCircuitBreaker(id);
    }

}
