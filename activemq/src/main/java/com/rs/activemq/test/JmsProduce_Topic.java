package com.rs.activemq.test;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 *  ActiveMQ 消息生产者 Topic 主题订阅模式
 */
public class JmsProduce_Topic {

    private static final String ACTIVEMQ_URL = "tcp://192.168.3.30:61616";
    private static final String TOPIC_NAME = "topic01";

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(TOPIC_NAME);
        MessageProducer producer = session.createProducer(topic);

        for (int i = 1; i <= 3; i++){
            TextMessage textMessage = session.createTextMessage("topic 订阅消息：" + i);
            producer.send(textMessage);
        }

        producer.close();
        session.close();
        connection.close();

    }

}
