package com.study.produce;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author jiayq
 */
public class Produce {

    private final static String QUEUE_NAME = "hello_rabbit";

    public static void main(String[] args) {
        //创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //设置目标主机
        connectionFactory.setHost("localhost");
        try (
                //创建连接
                Connection connection = connectionFactory.newConnection();
                //创建信道
                Channel channel = connection.createChannel()
                ) {
            //声明队列
            channel.queueDeclare(QUEUE_NAME,false,false,false,null);
            //创建消息
            String message = "Hello World";
            //生产消息
            channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
            System.out.println("publish");
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
