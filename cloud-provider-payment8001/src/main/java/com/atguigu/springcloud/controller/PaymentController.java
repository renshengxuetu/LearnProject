package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import org.apache.ibatis.annotations.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    protected PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;
    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int sum = paymentService.create(payment);
        log.info("******插入结果:" + sum);
        if(sum > 0){
            return new CommonResult(200, "插入数据库成功, serverPort:" + serverPort , sum);
        }else{
            return new CommonResult(444, "插入数据库失败");
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("******查询结果:" + payment);
        if(payment != null){
            return new CommonResult(200, "查询数据库成功, serverPort:" + serverPort, payment);
        }else{
            return new CommonResult(444, "没有对应记录，查询ID:" + id);
        }
    }

    @GetMapping(value = "/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();//获取在Eureka注册的服务名列
        for (String service: services) {
            log.info("*******服务:" + service);
        }
        //获取在Eureka注册的服务下的实例对象列表
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        for (ServiceInstance instance:instances) {
            log.info("*******实例:" + instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
        }
        return discoveryClient;
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout(){
        try{
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return serverPort;
    }

    @GetMapping(value="/payment/zipkin")
    public String zipkin(){
        PaymentControllerImpl c = new PaymentControllerImpl();
        c.test();
        return "啦啦啦 (*^_^*)！";
    }

}
