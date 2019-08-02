package com.study.exchange.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.study.exchange.config.RabbitMqConfig;
import com.study.exchange.neum.RabbitMqExchangeModel;

import java.util.Random;

/**
 * @author jiayq
 */
public class Produce {

    public static void main(String[] args) throws Exception{
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
        channel.exchangeDeclare(RabbitMqConfig.EXCHANGE_NAME_C, RabbitMqExchangeModel.DIRECT.getCode(),false,false,null);channel.exchangeDeclare(RabbitMqConfig.EXCHANGE_NAME_A, RabbitMqExchangeModel.DIRECT.getCode(),false,false,null);
        channel.exchangeDeclare(RabbitMqConfig.EXCHANGE_NAME_D, RabbitMqExchangeModel.DIRECT.getCode(),false,false,null);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_A, RabbitMqConfig.EXCHANGE_NAME_A, RabbitMqConfig.ROUNT_KEY_A);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_B, RabbitMqConfig.EXCHANGE_NAME_B, RabbitMqConfig.ROUNT_KEY_B);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_C, RabbitMqConfig.EXCHANGE_NAME_C, RabbitMqConfig.ROUNT_KEY_C);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_D, RabbitMqConfig.EXCHANGE_NAME_D, RabbitMqConfig.ROUNT_KEY_D);
        while (true) {
            String message_A = new Random().nextDouble() + "";
            String message_B = new Random().nextDouble() + "";
            String message_C = new Random().nextDouble() + "";
            String message_D = new Random().nextDouble() + "";
            channel.basicPublish(RabbitMqConfig.EXCHANGE_NAME_A, RabbitMqConfig.ROUNT_KEY_A, null, message_A.getBytes());
            channel.basicPublish(RabbitMqConfig.EXCHANGE_NAME_B, RabbitMqConfig.ROUNT_KEY_B, null, message_B.getBytes());
            channel.basicPublish(RabbitMqConfig.EXCHANGE_NAME_C, RabbitMqConfig.ROUNT_KEY_C, null, message_C.getBytes());
            channel.basicPublish(RabbitMqConfig.EXCHANGE_NAME_D, RabbitMqConfig.ROUNT_KEY_D, null, message_D.getBytes());
            Thread.sleep(3 * 1000);
        }

    }

}
