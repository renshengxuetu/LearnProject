package com.rs.activemq.boot;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

/**
 * springBoot 整合 ActiveMQ 队列消费者
 */
@Component
public class Queue_consumer {

    /**
     * 使用 springboot 的监听器注解来进行监听 ActiveMQ 消息
     * @param textMessage
     * @throws JMSException
     */
    @JmsListener(destination = "${myqueue}")
    public void receive(TextMessage textMessage) throws JMSException {
        System.out.println("接受到的消息：" + textMessage.getText());
    }

}
