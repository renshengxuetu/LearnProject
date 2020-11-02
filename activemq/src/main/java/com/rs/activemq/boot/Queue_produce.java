package com.rs.activemq.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.Queue;
import java.util.UUID;

/**
 * SpringBoot 整合 ActiveMQ 队列生产者
 */
@Component
public class Queue_produce {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    public void produceMsg(){
        jmsMessagingTemplate.convertAndSend(queue, "*******: " + UUID.randomUUID().toString().substring(0, 6));
    }

    /**
     * 定时 每隔三秒发送一条消息到MQ
     */
    //@Scheduled(fixedDelay = 3000)
    public void scheduledMsg(){
        MessageConverter messageConverter = jmsMessagingTemplate.getMessageConverter();
        jmsMessagingTemplate.convertAndSend(queue, "*******: " + UUID.randomUUID().toString().substring(0, 6));
    }

}
