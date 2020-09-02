package com.atguigu.springcloud.alibaba.controller;

import com.atguigu.springcloud.alibaba.service.AccountService;
import com.atguigu.springcloud.entities.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class AccountController {

    @Resource
    private AccountService accountService;

    @PostMapping(value="/account/decrease")
    public CommonResult decrease(@RequestParam("userId")Long userId, @RequestParam("money")Integer money){
        log.info("------>开始减余额");
        try{
            TimeUnit.SECONDS.sleep(20);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        accountService.decrease(userId, money);
        log.info("------>减余额 END");

        return new CommonResult(200, "减余额成功(*^_^*)");
    }

}
