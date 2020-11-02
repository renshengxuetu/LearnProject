package com.rs.activemq.test;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * ActiveMQ 消费者 持久化的 主题订阅模式
 */
public class JmsConsumer_Topic_Durable {

    private static final String ACTIVEMQ_URL = "tcp://192.168.3.30:61616";
    private static final String TOPIC_NAME = "durable_topic01";

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.setClientID("c01");      // 设置一个订阅者客户端ID，必须要设置

        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
        Topic topic = session.createTopic(TOPIC_NAME);
        // 创建一个持久化的订阅者
        TopicSubscriber durableSubscriber = session.createDurableSubscriber(topic, "remark...");

        connection.start();

        Message receive = durableSubscriber.receive();
        while (null != receive){
            TextMessage textMessage = (TextMessage)receive;
            textMessage.acknowledge();
            System.out.println("接受到的 durable message :" + textMessage.getText());
            receive = durableSubscriber.receive(4000L);
        }

        durableSubscriber.close();
        session.close();
        connection.close();

    }

}
