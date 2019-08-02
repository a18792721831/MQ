package com.study.exchange.direct;

import com.rabbitmq.client.*;
import com.study.exchange.config.RabbitMqConfig;
import org.junit.Test;

/**
 * @author jiayq
 */
public class Main {

    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(RabbitMqConfig.HOST_NAME);
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        DeliverCallback deliverCallback = (tags, message) -> {
            System.out.println("message:\t" + new String(message.getBody(),"utf-8"));
            System.out.println("exchange:\t" + message.getEnvelope().getExchange());
            System.out.println("rountKey:\t" + message.getEnvelope().getRoutingKey());
        };
        CancelCallback cancelCallback = (tag) -> {
            System.out.println("tag:\t" + tag);
        };
        channel.basicConsume(RabbitMqConfig.QUEUE_NAME_A, true,deliverCallback,cancelCallback);
    }

    @Test
    public void test1() throws  Exception{
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(RabbitMqConfig.HOST_NAME);
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_A, RabbitMqConfig.EXCHANGE_NAME_A, RabbitMqConfig.ROUNT_KEY_B);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_A, RabbitMqConfig.EXCHANGE_NAME_A, RabbitMqConfig.ROUNT_KEY_C);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_A, RabbitMqConfig.EXCHANGE_NAME_A, RabbitMqConfig.ROUNT_KEY_D);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_A, RabbitMqConfig.EXCHANGE_NAME_B, RabbitMqConfig.ROUNT_KEY_A);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_A, RabbitMqConfig.EXCHANGE_NAME_B, RabbitMqConfig.ROUNT_KEY_B);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_A, RabbitMqConfig.EXCHANGE_NAME_B, RabbitMqConfig.ROUNT_KEY_C);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_A, RabbitMqConfig.EXCHANGE_NAME_B, RabbitMqConfig.ROUNT_KEY_D);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_A, RabbitMqConfig.EXCHANGE_NAME_C, RabbitMqConfig.ROUNT_KEY_A);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_A, RabbitMqConfig.EXCHANGE_NAME_C, RabbitMqConfig.ROUNT_KEY_B);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_A, RabbitMqConfig.EXCHANGE_NAME_C, RabbitMqConfig.ROUNT_KEY_C);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_A, RabbitMqConfig.EXCHANGE_NAME_C, RabbitMqConfig.ROUNT_KEY_D);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_A, RabbitMqConfig.EXCHANGE_NAME_D, RabbitMqConfig.ROUNT_KEY_A);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_A, RabbitMqConfig.EXCHANGE_NAME_D, RabbitMqConfig.ROUNT_KEY_B);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_A, RabbitMqConfig.EXCHANGE_NAME_D, RabbitMqConfig.ROUNT_KEY_C);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_A, RabbitMqConfig.EXCHANGE_NAME_D, RabbitMqConfig.ROUNT_KEY_D);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_B, RabbitMqConfig.EXCHANGE_NAME_A, RabbitMqConfig.ROUNT_KEY_B);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_B, RabbitMqConfig.EXCHANGE_NAME_A, RabbitMqConfig.ROUNT_KEY_C);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_B, RabbitMqConfig.EXCHANGE_NAME_A, RabbitMqConfig.ROUNT_KEY_D);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_B, RabbitMqConfig.EXCHANGE_NAME_B, RabbitMqConfig.ROUNT_KEY_A);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_B, RabbitMqConfig.EXCHANGE_NAME_B, RabbitMqConfig.ROUNT_KEY_B);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_B, RabbitMqConfig.EXCHANGE_NAME_B, RabbitMqConfig.ROUNT_KEY_C);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_B, RabbitMqConfig.EXCHANGE_NAME_B, RabbitMqConfig.ROUNT_KEY_D);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_B, RabbitMqConfig.EXCHANGE_NAME_C, RabbitMqConfig.ROUNT_KEY_A);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_B, RabbitMqConfig.EXCHANGE_NAME_C, RabbitMqConfig.ROUNT_KEY_B);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_B, RabbitMqConfig.EXCHANGE_NAME_C, RabbitMqConfig.ROUNT_KEY_C);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_B, RabbitMqConfig.EXCHANGE_NAME_C, RabbitMqConfig.ROUNT_KEY_D);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_B, RabbitMqConfig.EXCHANGE_NAME_D, RabbitMqConfig.ROUNT_KEY_A);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_B, RabbitMqConfig.EXCHANGE_NAME_D, RabbitMqConfig.ROUNT_KEY_B);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_B, RabbitMqConfig.EXCHANGE_NAME_D, RabbitMqConfig.ROUNT_KEY_C);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_B, RabbitMqConfig.EXCHANGE_NAME_D, RabbitMqConfig.ROUNT_KEY_D);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_C, RabbitMqConfig.EXCHANGE_NAME_A, RabbitMqConfig.ROUNT_KEY_B);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_C, RabbitMqConfig.EXCHANGE_NAME_A, RabbitMqConfig.ROUNT_KEY_C);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_C, RabbitMqConfig.EXCHANGE_NAME_A, RabbitMqConfig.ROUNT_KEY_D);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_C, RabbitMqConfig.EXCHANGE_NAME_B, RabbitMqConfig.ROUNT_KEY_A);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_C, RabbitMqConfig.EXCHANGE_NAME_B, RabbitMqConfig.ROUNT_KEY_B);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_C, RabbitMqConfig.EXCHANGE_NAME_B, RabbitMqConfig.ROUNT_KEY_C);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_C, RabbitMqConfig.EXCHANGE_NAME_B, RabbitMqConfig.ROUNT_KEY_D);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_C, RabbitMqConfig.EXCHANGE_NAME_C, RabbitMqConfig.ROUNT_KEY_A);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_C, RabbitMqConfig.EXCHANGE_NAME_C, RabbitMqConfig.ROUNT_KEY_B);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_C, RabbitMqConfig.EXCHANGE_NAME_C, RabbitMqConfig.ROUNT_KEY_C);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_C, RabbitMqConfig.EXCHANGE_NAME_C, RabbitMqConfig.ROUNT_KEY_D);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_C, RabbitMqConfig.EXCHANGE_NAME_D, RabbitMqConfig.ROUNT_KEY_A);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_C, RabbitMqConfig.EXCHANGE_NAME_D, RabbitMqConfig.ROUNT_KEY_B);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_C, RabbitMqConfig.EXCHANGE_NAME_D, RabbitMqConfig.ROUNT_KEY_C);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_C, RabbitMqConfig.EXCHANGE_NAME_D, RabbitMqConfig.ROUNT_KEY_D);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_D, RabbitMqConfig.EXCHANGE_NAME_A, RabbitMqConfig.ROUNT_KEY_B);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_D, RabbitMqConfig.EXCHANGE_NAME_A, RabbitMqConfig.ROUNT_KEY_C);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_D, RabbitMqConfig.EXCHANGE_NAME_A, RabbitMqConfig.ROUNT_KEY_D);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_D, RabbitMqConfig.EXCHANGE_NAME_B, RabbitMqConfig.ROUNT_KEY_A);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_D, RabbitMqConfig.EXCHANGE_NAME_B, RabbitMqConfig.ROUNT_KEY_B);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_D, RabbitMqConfig.EXCHANGE_NAME_B, RabbitMqConfig.ROUNT_KEY_C);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_D, RabbitMqConfig.EXCHANGE_NAME_B, RabbitMqConfig.ROUNT_KEY_D);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_D, RabbitMqConfig.EXCHANGE_NAME_C, RabbitMqConfig.ROUNT_KEY_A);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_D, RabbitMqConfig.EXCHANGE_NAME_C, RabbitMqConfig.ROUNT_KEY_B);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_D, RabbitMqConfig.EXCHANGE_NAME_C, RabbitMqConfig.ROUNT_KEY_C);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_D, RabbitMqConfig.EXCHANGE_NAME_C, RabbitMqConfig.ROUNT_KEY_D);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_D, RabbitMqConfig.EXCHANGE_NAME_D, RabbitMqConfig.ROUNT_KEY_A);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_D, RabbitMqConfig.EXCHANGE_NAME_D, RabbitMqConfig.ROUNT_KEY_B);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_D, RabbitMqConfig.EXCHANGE_NAME_D, RabbitMqConfig.ROUNT_KEY_C);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_D, RabbitMqConfig.EXCHANGE_NAME_D, RabbitMqConfig.ROUNT_KEY_D);
    }

    @Test
    public void test2() throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(RabbitMqConfig.HOST_NAME);
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueUnbind(RabbitMqConfig.QUEUE_NAME_A, RabbitMqConfig.EXCHANGE_NAME_A, RabbitMqConfig.ROUNT_KEY_A);
        channel.queueUnbind(RabbitMqConfig.QUEUE_NAME_A, RabbitMqConfig.EXCHANGE_NAME_A, RabbitMqConfig.ROUNT_KEY_B);
        channel.queueUnbind(RabbitMqConfig.QUEUE_NAME_A, RabbitMqConfig.EXCHANGE_NAME_A, RabbitMqConfig.ROUNT_KEY_C);
        channel.queueUnbind(RabbitMqConfig.QUEUE_NAME_A, RabbitMqConfig.EXCHANGE_NAME_A, RabbitMqConfig.ROUNT_KEY_D);
        channel.queueUnbind(RabbitMqConfig.QUEUE_NAME_A, RabbitMqConfig.EXCHANGE_NAME_B, RabbitMqConfig.ROUNT_KEY_A);
        channel.queueUnbind(RabbitMqConfig.QUEUE_NAME_A, RabbitMqConfig.EXCHANGE_NAME_B, RabbitMqConfig.ROUNT_KEY_B);
        channel.queueUnbind(RabbitMqConfig.QUEUE_NAME_A, RabbitMqConfig.EXCHANGE_NAME_B, RabbitMqConfig.ROUNT_KEY_C);
        channel.queueUnbind(RabbitMqConfig.QUEUE_NAME_A, RabbitMqConfig.EXCHANGE_NAME_B, RabbitMqConfig.ROUNT_KEY_D);
        channel.queueUnbind(RabbitMqConfig.QUEUE_NAME_A, RabbitMqConfig.EXCHANGE_NAME_C, RabbitMqConfig.ROUNT_KEY_A);
        channel.queueUnbind(RabbitMqConfig.QUEUE_NAME_A, RabbitMqConfig.EXCHANGE_NAME_C, RabbitMqConfig.ROUNT_KEY_B);
        channel.queueUnbind(RabbitMqConfig.QUEUE_NAME_A, RabbitMqConfig.EXCHANGE_NAME_C, RabbitMqConfig.ROUNT_KEY_C);
        channel.queueUnbind(RabbitMqConfig.QUEUE_NAME_A, RabbitMqConfig.EXCHANGE_NAME_C, RabbitMqConfig.ROUNT_KEY_D);
        channel.queueUnbind(RabbitMqConfig.QUEUE_NAME_A, RabbitMqConfig.EXCHANGE_NAME_D, RabbitMqConfig.ROUNT_KEY_A);
        channel.queueUnbind(RabbitMqConfig.QUEUE_NAME_A, RabbitMqConfig.EXCHANGE_NAME_D, RabbitMqConfig.ROUNT_KEY_B);
        channel.queueUnbind(RabbitMqConfig.QUEUE_NAME_A, RabbitMqConfig.EXCHANGE_NAME_D, RabbitMqConfig.ROUNT_KEY_C);
        channel.queueUnbind(RabbitMqConfig.QUEUE_NAME_A, RabbitMqConfig.EXCHANGE_NAME_D, RabbitMqConfig.ROUNT_KEY_D);
        channel.queueUnbind(RabbitMqConfig.QUEUE_NAME_B, RabbitMqConfig.EXCHANGE_NAME_A, RabbitMqConfig.ROUNT_KEY_A);
        channel.queueUnbind(RabbitMqConfig.QUEUE_NAME_B, RabbitMqConfig.EXCHANGE_NAME_A, RabbitMqConfig.ROUNT_KEY_B);
        channel.queueUnbind(RabbitMqConfig.QUEUE_NAME_B, RabbitMqConfig.EXCHANGE_NAME_A, RabbitMqConfig.ROUNT_KEY_C);
        channel.queueUnbind(RabbitMqConfig.QUEUE_NAME_B, RabbitMqConfig.EXCHANGE_NAME_A, RabbitMqConfig.ROUNT_KEY_D);
        channel.queueUnbind(RabbitMqConfig.QUEUE_NAME_B, RabbitMqConfig.EXCHANGE_NAME_B, RabbitMqConfig.ROUNT_KEY_A);
        channel.queueUnbind(RabbitMqConfig.QUEUE_NAME_B, RabbitMqConfig.EXCHANGE_NAME_B, RabbitMqConfig.ROUNT_KEY_B);
        channel.queueUnbind(RabbitMqConfig.QUEUE_NAME_B, RabbitMqConfig.EXCHANGE_NAME_B, RabbitMqConfig.ROUNT_KEY_C);
        channel.queueUnbind(RabbitMqConfig.QUEUE_NAME_B, RabbitMqConfig.EXCHANGE_NAME_B, RabbitMqConfig.ROUNT_KEY_D);
        channel.queueUnbind(RabbitMqConfig.QUEUE_NAME_B, RabbitMqConfig.EXCHANGE_NAME_C, RabbitMqConfig.ROUNT_KEY_A);
        channel.queueUnbind(RabbitMqConfig.QUEUE_NAME_B, RabbitMqConfig.EXCHANGE_NAME_C, RabbitMqConfig.ROUNT_KEY_B);
        channel.queueUnbind(RabbitMqConfig.QUEUE_NAME_B, RabbitMqConfig.EXCHANGE_NAME_C, RabbitMqConfig.ROUNT_KEY_C);
        channel.queueUnbind(RabbitMqConfig.QUEUE_NAME_B, RabbitMqConfig.EXCHANGE_NAME_C, RabbitMqConfig.ROUNT_KEY_D);
        channel.queueUnbind(RabbitMqConfig.QUEUE_NAME_B, RabbitMqConfig.EXCHANGE_NAME_D, RabbitMqConfig.ROUNT_KEY_A);
        channel.queueUnbind(RabbitMqConfig.QUEUE_NAME_B, RabbitMqConfig.EXCHANGE_NAME_D, RabbitMqConfig.ROUNT_KEY_B);
        channel.queueUnbind(RabbitMqConfig.QUEUE_NAME_B, RabbitMqConfig.EXCHANGE_NAME_D, RabbitMqConfig.ROUNT_KEY_C);
        channel.queueUnbind(RabbitMqConfig.QUEUE_NAME_B, RabbitMqConfig.EXCHANGE_NAME_D, RabbitMqConfig.ROUNT_KEY_D);
        channel.queueUnbind(RabbitMqConfig.QUEUE_NAME_C, RabbitMqConfig.EXCHANGE_NAME_A, RabbitMqConfig.ROUNT_KEY_A);
        channel.queueUnbind(RabbitMqConfig.QUEUE_NAME_C, RabbitMqConfig.EXCHANGE_NAME_A, RabbitMqConfig.ROUNT_KEY_B);
        channel.queueUnbind(RabbitMqConfig.QUEUE_NAME_C, RabbitMqConfig.EXCHANGE_NAME_A, RabbitMqConfig.ROUNT_KEY_C);
        channel.queueUnbind(RabbitMqConfig.QUEUE_NAME_C, RabbitMqConfig.EXCHANGE_NAME_A, RabbitMqConfig.ROUNT_KEY_D);
        channel.queueUnbind(RabbitMqConfig.QUEUE_NAME_C, RabbitMqConfig.EXCHANGE_NAME_B, RabbitMqConfig.ROUNT_KEY_A);
        channel.queueUnbind(RabbitMqConfig.QUEUE_NAME_C, RabbitMqConfig.EXCHANGE_NAME_B, RabbitMqConfig.ROUNT_KEY_B);
        channel.queueUnbind(RabbitMqConfig.QUEUE_NAME_C, RabbitMqConfig.EXCHANGE_NAME_B, RabbitMqConfig.ROUNT_KEY_C);
        channel.queueUnbind(RabbitMqConfig.QUEUE_NAME_C, RabbitMqConfig.EXCHANGE_NAME_B, RabbitMqConfig.ROUNT_KEY_D);
        channel.queueUnbind(RabbitMqConfig.QUEUE_NAME_C, RabbitMqConfig.EXCHANGE_NAME_C, RabbitMqConfig.ROUNT_KEY_A);
        channel.queueUnbind(RabbitMqConfig.QUEUE_NAME_C, RabbitMqConfig.EXCHANGE_NAME_C, RabbitMqConfig.ROUNT_KEY_B);
        channel.queueUnbind(RabbitMqConfig.QUEUE_NAME_C, RabbitMqConfig.EXCHANGE_NAME_C, RabbitMqConfig.ROUNT_KEY_C);
        channel.queueUnbind(RabbitMqConfig.QUEUE_NAME_C, RabbitMqConfig.EXCHANGE_NAME_C, RabbitMqConfig.ROUNT_KEY_D);
        channel.queueUnbind(RabbitMqConfig.QUEUE_NAME_C, RabbitMqConfig.EXCHANGE_NAME_D, RabbitMqConfig.ROUNT_KEY_A);
        channel.queueUnbind(RabbitMqConfig.QUEUE_NAME_C, RabbitMqConfig.EXCHANGE_NAME_D, RabbitMqConfig.ROUNT_KEY_B);
        channel.queueUnbind(RabbitMqConfig.QUEUE_NAME_C, RabbitMqConfig.EXCHANGE_NAME_D, RabbitMqConfig.ROUNT_KEY_C);
        channel.queueUnbind(RabbitMqConfig.QUEUE_NAME_C, RabbitMqConfig.EXCHANGE_NAME_D, RabbitMqConfig.ROUNT_KEY_D);
        channel.queueUnbind(RabbitMqConfig.QUEUE_NAME_D, RabbitMqConfig.EXCHANGE_NAME_A, RabbitMqConfig.ROUNT_KEY_A);
        channel.queueUnbind(RabbitMqConfig.QUEUE_NAME_D, RabbitMqConfig.EXCHANGE_NAME_A, RabbitMqConfig.ROUNT_KEY_B);
        channel.queueUnbind(RabbitMqConfig.QUEUE_NAME_D, RabbitMqConfig.EXCHANGE_NAME_A, RabbitMqConfig.ROUNT_KEY_C);
        channel.queueUnbind(RabbitMqConfig.QUEUE_NAME_D, RabbitMqConfig.EXCHANGE_NAME_A, RabbitMqConfig.ROUNT_KEY_D);
        channel.queueUnbind(RabbitMqConfig.QUEUE_NAME_D, RabbitMqConfig.EXCHANGE_NAME_B, RabbitMqConfig.ROUNT_KEY_A);
        channel.queueUnbind(RabbitMqConfig.QUEUE_NAME_D, RabbitMqConfig.EXCHANGE_NAME_B, RabbitMqConfig.ROUNT_KEY_B);
        channel.queueUnbind(RabbitMqConfig.QUEUE_NAME_D, RabbitMqConfig.EXCHANGE_NAME_B, RabbitMqConfig.ROUNT_KEY_C);
        channel.queueUnbind(RabbitMqConfig.QUEUE_NAME_D, RabbitMqConfig.EXCHANGE_NAME_B, RabbitMqConfig.ROUNT_KEY_D);
        channel.queueUnbind(RabbitMqConfig.QUEUE_NAME_D, RabbitMqConfig.EXCHANGE_NAME_C, RabbitMqConfig.ROUNT_KEY_A);
        channel.queueUnbind(RabbitMqConfig.QUEUE_NAME_D, RabbitMqConfig.EXCHANGE_NAME_C, RabbitMqConfig.ROUNT_KEY_B);
        channel.queueUnbind(RabbitMqConfig.QUEUE_NAME_D, RabbitMqConfig.EXCHANGE_NAME_C, RabbitMqConfig.ROUNT_KEY_C);
        channel.queueUnbind(RabbitMqConfig.QUEUE_NAME_D, RabbitMqConfig.EXCHANGE_NAME_C, RabbitMqConfig.ROUNT_KEY_D);
        channel.queueUnbind(RabbitMqConfig.QUEUE_NAME_D, RabbitMqConfig.EXCHANGE_NAME_D, RabbitMqConfig.ROUNT_KEY_A);
        channel.queueUnbind(RabbitMqConfig.QUEUE_NAME_D, RabbitMqConfig.EXCHANGE_NAME_D, RabbitMqConfig.ROUNT_KEY_B);
        channel.queueUnbind(RabbitMqConfig.QUEUE_NAME_D, RabbitMqConfig.EXCHANGE_NAME_D, RabbitMqConfig.ROUNT_KEY_C);
        channel.queueUnbind(RabbitMqConfig.QUEUE_NAME_D, RabbitMqConfig.EXCHANGE_NAME_D, RabbitMqConfig.ROUNT_KEY_D);
    }

    /**
     * A --A\B\C\D
     * B --B
     * C --B\C
     * D --A\D
     * @throws Exception
     */
    @Test
    public void test3() throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(RabbitMqConfig.HOST_NAME);
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_A, RabbitMqConfig.EXCHANGE_NAME_A, RabbitMqConfig.ROUNT_KEY_A);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_B, RabbitMqConfig.EXCHANGE_NAME_A, RabbitMqConfig.ROUNT_KEY_B);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_C, RabbitMqConfig.EXCHANGE_NAME_A, RabbitMqConfig.ROUNT_KEY_C);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_D, RabbitMqConfig.EXCHANGE_NAME_A, RabbitMqConfig.ROUNT_KEY_D);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_B, RabbitMqConfig.EXCHANGE_NAME_B, RabbitMqConfig.ROUNT_KEY_B);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_B, RabbitMqConfig.EXCHANGE_NAME_C, RabbitMqConfig.ROUNT_KEY_B);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_C, RabbitMqConfig.EXCHANGE_NAME_C, RabbitMqConfig.ROUNT_KEY_C);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_A, RabbitMqConfig.EXCHANGE_NAME_D, RabbitMqConfig.ROUNT_KEY_A);
        channel.queueBind(RabbitMqConfig.QUEUE_NAME_D, RabbitMqConfig.EXCHANGE_NAME_D, RabbitMqConfig.ROUNT_KEY_D);
    }

    /**
     * A --A\B\C\D
     * B --B
     * C --B\C
     * D --A\D
     */
    @Test
    public void test4() throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(RabbitMqConfig.HOST_NAME);
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
//        sendMessageEXA(channel, "test4_A");
//        sendMessageEXB(channel, "test4_B");
//        sendMessageEXC(channel, "test4_C");
        sendMessageEXD(channel, "test4_D");
    }

    private void sendMessageEXA(Channel channel, String message) throws Exception {
        channel.basicPublish(RabbitMqConfig.EXCHANGE_NAME_A, RabbitMqConfig.ROUNT_KEY_A, null, message.getBytes());
        channel.basicPublish(RabbitMqConfig.EXCHANGE_NAME_A, RabbitMqConfig.ROUNT_KEY_B, null, message.getBytes());
        channel.basicPublish(RabbitMqConfig.EXCHANGE_NAME_A, RabbitMqConfig.ROUNT_KEY_C, null, message.getBytes());
        channel.basicPublish(RabbitMqConfig.EXCHANGE_NAME_A, RabbitMqConfig.ROUNT_KEY_D, null, message.getBytes());
    }

    private void sendMessageEXB(Channel channel, String message) throws Exception {
        channel.basicPublish(RabbitMqConfig.EXCHANGE_NAME_B, RabbitMqConfig.ROUNT_KEY_B, null, message.getBytes());
    }

    private void sendMessageEXC(Channel channel, String message) throws Exception {
        channel.basicPublish(RabbitMqConfig.EXCHANGE_NAME_C, RabbitMqConfig.ROUNT_KEY_B, null, message.getBytes());
        channel.basicPublish(RabbitMqConfig.EXCHANGE_NAME_C, RabbitMqConfig.ROUNT_KEY_C, null, message.getBytes());
    }

    private void sendMessageEXD(Channel channel, String message) throws Exception {
        channel.basicPublish(RabbitMqConfig.EXCHANGE_NAME_D, RabbitMqConfig.ROUNT_KEY_A, null, message.getBytes());
        channel.basicPublish(RabbitMqConfig.EXCHANGE_NAME_D, RabbitMqConfig.ROUNT_KEY_D, null, message.getBytes());
    }
}
