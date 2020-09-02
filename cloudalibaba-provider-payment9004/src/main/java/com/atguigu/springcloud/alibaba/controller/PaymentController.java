package com.atguigu.springcloud.alibaba.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    public static HashMap<Long, Payment> hashMap = new HashMap<>();

    static{
        hashMap.put(1L, new Payment(1L, "23423dsgsdgdfh234"));
        hashMap.put(2L, new Payment(2L, "567567sgsdgdfh234"));
        hashMap.put(3L, new Payment(3L, "12354sdgsdgdfh234"));
    }

    @GetMapping(value="/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id")Long id){
        Payment payment = hashMap.get(id);
        return new CommonResult<Payment>(200, "from mysql, serverPort: " + serverPort, payment);
    }

}
