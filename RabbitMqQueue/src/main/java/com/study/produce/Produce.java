package com.study.produce;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

/**
 * @author jiayq
 */
public class Produce {

    /**
     *  队列名字
     */
    private static final String QUEUE_NAME = "HelloQueue";

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("Please input the message(one row one message.quit to exits):");
                String message = scanner.nextLine();
                //quit退出
                if ("quit".equals(message)) {
                    System.out.println("Will be quit!!!");
                    break;
                }
                sendMessage(message);
            }
        }
    }

    private static void sendMessage(String message) {
        System.out.println("Will be send message:\t" + message);
        //创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //设置目标主机
        connectionFactory.setHost("localhost");
        try (
                //创建连接
                Connection connection = connectionFactory.newConnection();
                //创建信道
                Channel channel = connection.createChannel()) {
            //声明队列：队列如果存在就不会新建
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            //设置信道每次最大传输信息数
            channel.basicQos(1);
            //发布消息
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println("The message send over:\t" + message);
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
