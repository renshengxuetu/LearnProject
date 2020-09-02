package com.atguigu.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value="CLOUD-PAYMENT-HYSTRIX-SERVICE", fallback = PaymentHystrixServiceFallBack.class)
public interface PaymentHystrixService {

    @GetMapping(value = "/payment/hystrix/paymentInfo_OK/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id);

    @GetMapping(value = "/payment/hystrix/paymentInfo_Timeout/{id}")
    public String paymentInfo_Timeout(@PathVariable("id")Integer id);

}