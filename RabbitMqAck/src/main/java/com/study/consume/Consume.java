package com.study.consume;

import com.rabbitmq.client.*;
import com.study.config.RabbitMqConfig;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author jiayq
 */
public class Consume {

    /**
     * 消费者应答模式
     */
    private static boolean consumeAck = false ;

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(RabbitMqConfig.HOST_NAME);
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(RabbitMqConfig.QUEUE_NAME, true, false, false, null);
        channel.exchangeDeclare(RabbitMqConfig.EXCHANGE_NAME, "direct", true,false,null);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME, RabbitMqConfig.EXCHANGE_NAME, RabbitMqConfig.ROUNT_KEY);
        DeliverCallback deliverCallback;
        CancelCallback cancelCallback;
        if (consumeAck) {
            deliverCallback = (tag,message) -> {
                System.out.println("consumeAck:\t" + consumeAck);
                System.out.println("message:\t" + new String(message.getBody(),"utf-8"));
            };
            cancelCallback = (tag) -> {
                System.out.println("consume error,tag:\t" + tag);
            };
        } else {
            deliverCallback = (tag, message) -> {
                System.out.println("consumeAck:\t" + consumeAck);
                System.out.println("message:\t" + new String(message.getBody(),"utf-8"));
                channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
            };
            cancelCallback = (tag) -> {
                System.out.println("consumer error,tag:\t" + tag);
            };
        }
        channel.basicConsume(RabbitMqConfig.QUEUE_NAME, consumeAck, deliverCallback, cancelCallback);
    }

}
