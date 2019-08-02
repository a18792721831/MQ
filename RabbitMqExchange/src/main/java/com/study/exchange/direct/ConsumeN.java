package com.study.exchange.direct;

import com.rabbitmq.client.*;
import com.study.exchange.config.RabbitMqConfig;

import java.util.Random;

/**
 * @author jiayq
 */
public class ConsumeN {

    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(RabbitMqConfig.HOST_NAME);
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        DeliverCallback deliverCallback = (tags,message) -> {
            System.out.println("tags:\t" + tags);
            System.out.println("message:\t" + new String(message.getBody(),"utf-8"));
            System.out.println("rountKey:\t" + message.getEnvelope().getRoutingKey());
            System.out.println("exchange:\t" + message.getEnvelope().getExchange());
        };
        CancelCallback cancelCallback = (tags) -> {
            System.out.println("tags:\t" + tags);
        };
        channel.basicConsume(RabbitMqConfig.QUEUE_NAME_A, true, deliverCallback, cancelCallback);
        channel.basicConsume(RabbitMqConfig.QUEUE_NAME_B, true, deliverCallback, cancelCallback);
        channel.basicConsume(RabbitMqConfig.QUEUE_NAME_C, true, deliverCallback, cancelCallback);
        channel.basicConsume(RabbitMqConfig.QUEUE_NAME_D, true, deliverCallback, cancelCallback);
    }

}
