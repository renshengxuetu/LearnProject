package com.atguigu.springcloud.alibaba.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;

public class CustomerBlockHandler {

    public static CommonResult handleException(BlockException exception){
        return new CommonResult(444, "按客户定义，global handleException /(ㄒoㄒ)/~~");
    }

}
