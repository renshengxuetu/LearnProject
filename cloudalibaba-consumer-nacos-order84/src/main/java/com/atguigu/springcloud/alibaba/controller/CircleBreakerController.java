package com.atguigu.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.atguigu.springcloud.alibaba.service.PaymentService;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class CircleBreakerController {

    private static String SERVICE_URL = "http://nacos-payment-provider";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private PaymentService paymentService;

    @GetMapping(value="/consumer/fallback/{id}")
    @SentinelResource(value="fallback")
    public CommonResult<Payment> fallback(@PathVariable("id") Long id){
        CommonResult<Payment> result = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/" + id, CommonResult.class, id);
        if(id == 4){
            throw new IllegalArgumentException("IllegalArgumentException, 非法参数异常...");
        }else if(result.getData() == null){
            throw new NullPointerException("NullPointerException, 该 Id 没有对应记录, 空指针异常...");
        }

        return result;
    }

    @GetMapping(value = "/consumer/openfeign/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id){
        return paymentService.paymentSQL(id);
    }

}
