package com.study.consume;

import com.rabbitmq.client.*;
import com.study.config.RabbitMqConfig;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author jiayq
 */
public class LazyConsume {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(RabbitMqConfig.HOST_NAME);
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(RabbitMqConfig.QUEUE_NAME, true, false, false, null);
        channel.exchangeDeclare(RabbitMqConfig.EXCHANGE_NAME, "direct", true, false,null);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME, RabbitMqConfig.EXCHANGE_NAME, RabbitMqConfig.ROUNT_KEY);
        channel.basicQos(1);
        DeliverCallback deliverCallback = (tag, message) -> {
            System.out.println("get Message:\t" + new String(message.getBody(),"utf-8"));
            channel.basicReject(message.getEnvelope().getDeliveryTag(), true);
            channel.basicAck(message.getEnvelope().getDeliveryTag(), true);
        };
        CancelCallback cancelCallback = (tag) -> {
            System.out.println("consume error,tag:\t" + tag);
        };
        channel.basicConsume(RabbitMqConfig.QUEUE_NAME, false, deliverCallback, cancelCallback);
    }

}
