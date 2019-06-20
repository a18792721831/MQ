package com.study.produce;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

/**
 * @author jiayq
 */
public class Produce {

    private static final String QUEUE_NAME = "HelloQueue";

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("Please input the message(one row one message,quit to exits):");
                String message = scanner.nextLine();
                if ("quit".equals(message)) {
                    System.out.println("Will be quit!!!");
                    break;
                }
                sendMessage(message);
            }
        }
    }

    private static void sendMessage(String message) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        try (
                Connection connection = connectionFactory.newConnection();
                Channel channel = connection.createChannel()){
            //channel.queueDeclare:queueName(队列名称),durable(是否持久化),
            // exclusive(是否加锁),autoDelete(是否自动删除),参数
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            channel.basicQos(1);
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println("The message send over:\t" + message);
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
