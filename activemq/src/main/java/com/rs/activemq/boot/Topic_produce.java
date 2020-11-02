package com.rs.activemq.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Topic;
import java.util.UUID;


/**
 * springBoot 整合 ActiveMQ 主题生产者
 */
@Component
public class Topic_produce {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private Topic topic;

    @Scheduled(fixedDelay = 5000)
    public void scheduledMsg(){
        jmsMessagingTemplate.convertAndSend(topic, "topic 消息：" + UUID.randomUUID().toString().substring(0, 6));
    }

}
