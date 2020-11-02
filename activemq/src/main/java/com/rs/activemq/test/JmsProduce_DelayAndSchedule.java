package com.rs.activemq.test;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ScheduledMessage;

import javax.jms.*;

/**
 * ActiveMQ 生产者, 延迟和定时投递
 */
public class JmsProduce_DelayAndSchedule {

    private static final String ACTIVEMQ_URL = "tcp://192.168.3.30:61616";
    private static final String QUEUE_NAME = "delay";

    public static void main(String[] args) throws JMSException {
        // 创建连接工厂，使用指定的url地址和默认的用户名和密码
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        // 通过连接工厂获得连接connection并启动访问
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();

        // 创建会话session
        // 两个参数，第一个叫事务/第二个叫签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 创建目的地(具体是队列还是主题topic)
        Queue queue = session.createQueue(QUEUE_NAME);
        // 创建消费的生产者
        MessageProducer producer = session.createProducer(queue);
        // 将生产者设置成持久化模式，这样才会将数据存放在数据库中
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        // 通过使用消息生产这生产3条消息发送到MQ的队列里面
        for (int i = 1; i <= 3; i++){
            // 创建固定格式的消息
            TextMessage textMessage = session.createTextMessage("delay_msg---" + i);
            textMessage.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, 10* 1000);
            textMessage.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_PERIOD, 4 * 1000);
            textMessage.setIntProperty(ScheduledMessage.AMQ_SCHEDULED_REPEAT, 5);
            // 通过消息生产者发送给MQ
            producer.send(textMessage);
        }

        producer.close();
        session.close();
        connection.close();

        System.out.println("**** 消息发布到MQ完成 ****");
    }

}
