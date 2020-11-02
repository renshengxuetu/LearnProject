package com.rs.activemq.test;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQMessageProducer;
import org.apache.activemq.AsyncCallback;

import javax.jms.*;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * ActiveMQ 生产者异步投送模式，使用带回调函数的 send 方法来确保消息发送成功
 */
public class JmsProduce_AsyncSend {

    private static final String ACTIVEMQ_URL = "tcp://192.168.3.30:61616";
    private static final String QUEUE_NAME = "cluster";

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
        // 创建消费的生产者，使用带回调函数的 send 方法，producer 需要指定为 ActiveMQMessageProducer
        ActiveMQMessageProducer activeMQMessageProducer = (ActiveMQMessageProducer)session.createProducer(queue);
        // 通过使用消息生产这生产3条消息发送到MQ的队列里面
        for (int i = 1; i <= 3; i++){
            // 创建固定格式的消息
            TextMessage textMessage = session.createTextMessage("msg---" + i);
            // 设置一个 MessageId
            textMessage.setJMSMessageID(UUID.randomUUID().toString());
            String msgId = textMessage.getJMSMessageID();
            // 通过消息生产者使用带有回调函数的方法发送给MQ
            activeMQMessageProducer.send(textMessage, new AsyncCallback() {
                @Override
                public void onSuccess() {
                    System.out.println(msgId + "： 成功发送！");
                }

                @Override
                public void onException(JMSException e) {
                    System.out.println(msgId + "： 发送失败！");
                }
            });
            try{
                TimeUnit.SECONDS.sleep(5);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        activeMQMessageProducer.close();
        session.close();
        connection.close();

        System.out.println("**** 消息发布到MQ完成 ****");
    }

}
