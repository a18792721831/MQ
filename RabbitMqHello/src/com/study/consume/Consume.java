package com.study.consume;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

/**
 * @author jiayq
 */
public class Consume {

    private final static String QUEUE_NAME = "hello_rabbit";

    public static void main(String[] args) throws Exception{
        //创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //设置目标主机
        connectionFactory.setHost("localhost");
        //创建连接
        Connection connection = connectionFactory.newConnection();
        //创建信道
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //创建回调(ps:如果回调方法一直得不到执行，那么是否会发生阻塞？)
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "utf-8");
            System.out.println(message);
//            System.exit(0);
        };
        //消费目标队列消息
        channel.basicConsume(QUEUE_NAME,true,deliverCallback,consumerTage -> {});
    }

}
