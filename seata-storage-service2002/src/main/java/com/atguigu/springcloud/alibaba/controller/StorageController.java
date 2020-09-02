package com.atguigu.springcloud.alibaba.controller;

import com.atguigu.springcloud.alibaba.service.StorageService;
import com.atguigu.springcloud.entities.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class StorageController {

    @Resource
    private StorageService storageService;

    @PostMapping(value = "/storage/decrease")
    public CommonResult decrease(@RequestParam("productId")Long productId, @RequestParam("count")Integer count){
        log.info("------>开始减库存");
        storageService.decrease(productId, count);
        log.info("------>减库存 END");
        return new CommonResult(200, "减库存成功(*^_^*)");
    }

}
