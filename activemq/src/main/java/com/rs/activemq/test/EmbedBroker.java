package com.rs.activemq.test;

import org.apache.activemq.broker.BrokerService;

/**
 * 使用 java 代码启动一个 ActiveMQ 实例
 */
public class EmbedBroker {

    public static void main(String[] args) throws Exception {
        BrokerService brokerService = new BrokerService();
        brokerService.setUseJmx(true);
        brokerService.addConnector("tcp://localhost:61616");
        brokerService.start();
        System.in.read();
    }

}
