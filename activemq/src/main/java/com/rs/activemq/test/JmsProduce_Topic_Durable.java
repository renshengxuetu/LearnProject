package com.rs.activemq.test;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 *  ActiveMQ 消息生产者 持久化的 Topic 主题订阅模式
 */
public class JmsProduce_Topic_Durable {

    private static final String ACTIVEMQ_URL = "tcp://192.168.3.30:61616";
    private static final String TOPIC_NAME = "durable_topic01";

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = activeMQConnectionFactory.createConnection();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(TOPIC_NAME);
        MessageProducer producer = session.createProducer(topic);
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);      // 设置这是一个持久化的生产者

        connection.start();

        for (int i = 1; i <= 3; i++){
            TextMessage textMessage = session.createTextMessage("durable topic message ---- " + i);
            producer.send(textMessage);
        }

        producer.close();
        session.close();
        connection.close();

    }

}
