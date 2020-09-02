package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentHystrixServiceFallBack implements PaymentHystrixService {

    @Override
    public String paymentInfo_OK(Integer id) {
        return "PaymentHystrixServiceFallBack -> paymentInfo_OK 返回处理信息...";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "PaymentHystrixServiceFallBack -> paymentInfo_Timeout 返回处理信息...";
    }
}
