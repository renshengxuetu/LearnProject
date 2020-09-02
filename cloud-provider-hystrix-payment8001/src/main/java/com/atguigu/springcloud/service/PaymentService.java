package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Payment;

public interface PaymentService {

    public String paymentInfo_OK(Integer id);

    public String paymentInfo_Timeout(Integer id);

    public String paymentCircuitBreaker(Integer id);

    public String paymentCircuitBreaker_fallBack(Integer id);

}
