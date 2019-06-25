package com.study.exchange.topic;

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
        channel.queueDeclare("topic_queue_A", false, false, false, null);
        channel.queueDeclare("topic_queue_B", false, false, false, null);
        channel.queueDeclare("topic_queue_C", false, false, false, null);
        channel.queueDeclare("topic_queue_D", false, false, false, null);
        channel.exchangeDeclare("topic_exchange_A", RabbitMqExchangeModel.TOCPIC.getCode(), false,false,null);
        channel.exchangeDeclare("topic_exchange_B", RabbitMqExchangeModel.TOCPIC.getCode(), false,false,null);
        channel.exchangeDeclare("topic_exchange_C", RabbitMqExchangeModel.TOCPIC.getCode(), false,false,null);
        channel.exchangeDeclare("topic_exchange_D", RabbitMqExchangeModel.TOCPIC.getCode(), false,false,null);
        channel.queueBind("topic_queue_A", "topic_exchange_A", "*.A");
        channel.queueBind("topic_queue_A", "topic_exchange_B", "*.A");
        channel.queueBind("topic_queue_A", "topic_exchange_C", "*.A");
        channel.queueBind("topic_queue_A", "topic_exchange_D", "*.A");
        channel.queueBind("topic_queue_B", "topic_exchange_A", "*.B");
        channel.queueBind("topic_queue_B", "topic_exchange_C", "*.B");
        channel.queueBind("topic_queue_D", "topic_exchange_C", "*.D");

    }

}
