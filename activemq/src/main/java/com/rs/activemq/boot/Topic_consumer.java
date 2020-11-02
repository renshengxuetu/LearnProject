package com.rs.activemq.boot;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

/**
 * springBoot 整合 ActiveMQ 主题消费者
 */
@Component
public class Topic_consumer {

    @JmsListener(destination = "${mytopic}")
    public void receive(TextMessage textMessage) throws JMSException {
        System.out.println("订阅者消费到主题消息：" + textMessage.getText());
    }

}
