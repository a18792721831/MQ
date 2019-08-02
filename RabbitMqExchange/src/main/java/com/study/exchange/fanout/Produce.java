package com.study.exchange.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.study.exchange.config.RabbitMqConfig;
import com.study.exchange.neum.RabbitMqExchangeModel;

/**
 * @author jiayq
 */
public class Produce {

    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(RabbitMqConfig.HOST_NAME);
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(RabbitMqConfig.EXCHANGE_NAME_A, RabbitMqExchangeModel.FANOUT.getCode(),false,false,null);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_A, RabbitMqConfig.EXCHANGE_NAME_A,RabbitMqConfig.ROUNT_KEY_A);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_B, RabbitMqConfig.EXCHANGE_NAME_A, RabbitMqConfig.ROUNT_KEY_B);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_C, RabbitMqConfig.EXCHANGE_NAME_B, "");
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_C, RabbitMqConfig.EXCHANGE_NAME_C, RabbitMqConfig.ROUNT_KEY_C);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_D, RabbitMqConfig.EXCHANGE_NAME_C, RabbitMqConfig.ROUNT_KEY_C);
    }

}
