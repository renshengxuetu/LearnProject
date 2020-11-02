package com.rs.activemq.test;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * ActiveMQ 消费者, 延迟和定时投递
 */
public class JmsConsumer_DelayAndSchedule {

    private static final String ACTIVEMQ_URL = "tcp://192.168.3.30:61616";
    private static final String QUEUE_NAME = "delay";

    public static void main(String[] args) throws JMSException, IOException {
        // 创建连接工厂，使用指定的url地址和默认的用户名和密码
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        activeMQConnectionFactory.setUseAsyncSend(true);
        // 通过连接工厂获得连接connection并启动访问
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();

        // 创建会话session
        // 两个参数，第一个叫事务/第二个叫签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 创建目的地(具体是队列还是主题topic)
        Queue queue = session.createQueue(QUEUE_NAME);
        // 创建消费者
        MessageConsumer consumer = session.createConsumer(queue);
        // 使用监听器的方式异步监听
        consumer. setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                if(message != null && message instanceof TextMessage){
                    TextMessage textMessage = (TextMessage) message;
                    try {
                        System.out.println("************消费者接受到消息：" + textMessage.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        System.in.read();
        consumer.close();
        session.close();
        connection.close();
    }

}
