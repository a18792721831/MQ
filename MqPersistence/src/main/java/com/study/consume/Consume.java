package com.study.consume;

import com.rabbitmq.client.*;
import com.study.config.RabbitMqConfig;

/**
 * @author jiayq
 */
public class Consume {

    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(RabbitMqConfig.HOST_NAME);
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(RabbitMqConfig.QUEUE_NAME, true, false, false, null);
        channel.exchangeDeclare(RabbitMqConfig.EXCHANGE_NAME, "direct", true, false, null);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME, RabbitMqConfig.EXCHANGE_NAME, RabbitMqConfig.ROUNT_KEY);
        DeliverCallback deliverCallback = (tag, deliver) -> {
            System.out.println("Consumer get message:\t" + new String(deliver.getBody(),"utf-8"));
            System.out.println("appId:\t" + deliver.getProperties().getAppId());
        };
        CancelCallback cancelCallback = (tag) -> {
            System.out.println("Consumer get message error,tags:\t" + tag);
        };
        channel.basicConsume(RabbitMqConfig.QUEUE_NAME, true, deliverCallback, cancelCallback);
    }

}
