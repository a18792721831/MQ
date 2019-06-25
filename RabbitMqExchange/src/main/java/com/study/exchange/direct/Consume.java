package com.study.exchange.direct;

import com.rabbitmq.client.*;
import com.study.exchange.config.RabbitMqConfig;
import com.study.exchange.neum.RabbitMqExchangeModel;

/**
 * @author jiayq
 */
public class Consume {

    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(RabbitMqConfig.HOST_NAME);
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.basicQos(1);
        channel.queueDeclare(RabbitMqConfig.QUEUE_NAME_A, false, false, false, null);
        channel.queueDeclare(RabbitMqConfig.QUEUE_NAME_B, false, false, false, null);
        channel.queueDeclare(RabbitMqConfig.QUEUE_NAME_C, false, false, false, null);
        channel.queueDeclare(RabbitMqConfig.QUEUE_NAME_D, false, false, false, null);
        channel.exchangeDeclare(RabbitMqConfig.EXCHANGE_NAME_A, RabbitMqExchangeModel.DIRECT.getCode(),false,false,null);
        channel.exchangeDeclare(RabbitMqConfig.EXCHANGE_NAME_B, RabbitMqExchangeModel.DIRECT.getCode(),false,false,null);
        channel.exchangeDeclare(RabbitMqConfig.EXCHANGE_NAME_C, RabbitMqExchangeModel.DIRECT.getCode(),false,false,null);
        channel.exchangeDeclare(RabbitMqConfig.EXCHANGE_NAME_D, RabbitMqExchangeModel.DIRECT.getCode(),false,false,null);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_A, RabbitMqConfig.EXCHANGE_NAME_A, RabbitMqConfig.ROUNT_KEY_A);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_B, RabbitMqConfig.EXCHANGE_NAME_B, RabbitMqConfig.ROUNT_KEY_B);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_C, RabbitMqConfig.EXCHANGE_NAME_C, RabbitMqConfig.ROUNT_KEY_C);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_D, RabbitMqConfig.EXCHANGE_NAME_D, RabbitMqConfig.ROUNT_KEY_D);
        DeliverCallback deliverCallback = (tags,message) -> {
            System.out.println("message:\t" + new String(message.getBody(),"utf-8"));
            System.out.println("exchange:\t" + message.getEnvelope().getExchange());
            System.out.println("rountKey:\t" + message.getEnvelope().getRoutingKey());
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
