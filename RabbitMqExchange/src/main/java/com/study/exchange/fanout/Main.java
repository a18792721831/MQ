package com.study.exchange.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.study.exchange.config.RabbitMqConfig;
import com.study.exchange.neum.RabbitMqExchangeModel;
import org.junit.Test;

/**
 * @author jiayq
 */
public class Main {

    @Test
    public void test1() throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(RabbitMqConfig.HOST_NAME);
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(RabbitMqConfig.QUEUE_NAME_A, false, false, false, null);
        channel.queueDeclare(RabbitMqConfig.QUEUE_NAME_B, false, false, false, null);
        channel.queueDeclare(RabbitMqConfig.QUEUE_NAME_C, false, false, false, null);
        channel.queueDeclare(RabbitMqConfig.QUEUE_NAME_D, false, false, false, null);
        channel.exchangeDeclare(RabbitMqConfig.EXCHANGE_NAME_A, RabbitMqExchangeModel.FANOUT.getCode(),false,false,null);
        channel.exchangeDeclare(RabbitMqConfig.EXCHANGE_NAME_B, RabbitMqExchangeModel.FANOUT.getCode(),false,false,null);
        channel.exchangeDeclare(RabbitMqConfig.EXCHANGE_NAME_C, RabbitMqExchangeModel.FANOUT.getCode(),false,false,null);
        channel.exchangeDeclare(RabbitMqConfig.EXCHANGE_NAME_D, RabbitMqExchangeModel.FANOUT.getCode(),false,false,null);
    }

    @Test
    public void test2() throws Exception{
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(RabbitMqConfig.HOST_NAME);
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.basicPublish(RabbitMqConfig.EXCHANGE_NAME_A, RabbitMqConfig.ROUNT_KEY_C, null, "test2".getBytes());
    }

}
