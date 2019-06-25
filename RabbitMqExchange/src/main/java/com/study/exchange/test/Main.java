package com.study.exchange.test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.study.exchange.config.RabbitMqConfig;
import com.study.exchange.neum.RabbitMqExchangeModel;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author jiayq
 */
public class Main {

    private static final ConnectionFactory connectionFactory;

    private static Connection connection = null;

    private static Channel channel = null;

    static {
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(RabbitMqConfig.HOST_NAME);
        try {
            connection = connectionFactory.newConnection();
            channel = connection.createChannel();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1() throws Exception {
        channel.queueDeclare("direct_queue_A", false, false, false, null);
        channel.queueDeclare("driect_queue_B", false, false, false, null);
        channel.queueDeclare("driect_queue_C", false, false, false, null);
        channel.exchangeDeclare("direct_exchange_A", RabbitMqExchangeModel.DIRECT.getCode(), false, false, null);
        channel.exchangeDeclare("direct_exchange_B", RabbitMqExchangeModel.DIRECT.getCode(), false, false, null);
        channel.exchangeDeclare("direct_exchange_C", RabbitMqExchangeModel.DIRECT.getCode(), false, false, null);
        channel.queueBind("direct_queue_A", "direct_exchange_A", "direct_rountKey_A");
        channel.queueBind("driect_queue_B", "direct_exchange_A", "direct_rountKey_A");
    }

    @Test
    public void test2() throws Exception {
        channel.basicPublish("direct_exchange_A", "direct_rountKey_A", null, "direct_test2".getBytes());
    }

    @Test
    public void test3() throws Exception {
        channel.basicPublish("topic_exchange_A", "test.B", null, "test.B".getBytes());
    }

    @Test
    public void test4() throws Exception {
        channel.queueDeclare("queue1", false, false, false, null);
        channel.exchangeDeclare("exchange1", RabbitMqExchangeModel.DIRECT.getCode(), false, false, null);
        channel.queueBind("queue1", "exchange1", "");
        channel.exchangeBind("exchange1", "exchange1","" );
    }

    @Test
    public void test5() throws Exception {
        channel.basicPublish("exchange1", "", null, "bind.test".getBytes());
    }
}
