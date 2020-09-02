package com.atguigu.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.alibaba.myhandler.CustomerBlockHandler;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RateLimitController {

    @GetMapping(value = "/byResource")
    @SentinelResource(value = "byResource", blockHandler = "handleException")
    public CommonResult byResource(){
        return new CommonResult(200, "按资源名称限流测试ok", new Payment(2020L, "serial001"));
    }

    public CommonResult handleException(BlockException blockException){
        return new CommonResult(444, blockException.getClass().getCanonicalName() + "\t 按资源名称限流测试ok");
    }

    @GetMapping(value = "/ratelimit/byUrl")
    @SentinelResource(value = "byUrl", blockHandler = "handleException")
    public CommonResult byUrl(){
        return new CommonResult(200, "按Url限流测试ok", new Payment(2020L, "serial002"));
    }

    @GetMapping(value = "/ratelimit/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",
            blockHandlerClass = CustomerBlockHandler.class,
            blockHandler = "handleException")
    public CommonResult customerBlockHandler(){
        return new CommonResult(200, "按客户自定义", new Payment(2020L, "serial003"));
    }

}
