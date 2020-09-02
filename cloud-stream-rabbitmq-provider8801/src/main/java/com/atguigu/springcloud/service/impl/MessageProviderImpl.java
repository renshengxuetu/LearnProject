package com.atguigu.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.atguigu.springcloud.service.IMessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import javax.annotation.Resource;

@EnableBinding(Source.class)//定义消息的推送
@Slf4j
public class MessageProviderImpl implements IMessageProvider {

    @Resource
    private MessageChannel output; //消息发送管道

    @Override
    public String send() {
        String serial = IdUtil.simpleUUID();
        output.send(MessageBuilder.withPayload(serial).build());
        log.info("******" + serial);
        return serial;
    }

}
